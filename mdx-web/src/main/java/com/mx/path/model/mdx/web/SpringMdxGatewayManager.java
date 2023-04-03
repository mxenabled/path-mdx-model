package com.mx.path.model.mdx.web;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.Setter;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import com.mx.common.collections.ObjectMap;
import com.mx.common.serialization.ObjectMapYamlDeserializer;
import com.mx.path.gateway.api.Gateway;
import com.mx.path.gateway.api.GatewayConfigurator;
import com.mx.path.gateway.configuration.Configurator;
import com.mx.path.gateway.configuration.ConfiguratorObserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Launches gateway configuration using Spring application initialize hooks.
 *
 * <p>After initialization, contains registry of gateways by client id.
 *
 * <p>Reads configuration from gateway.yaml.
 */
@Component
public class SpringMdxGatewayManager {

  private static final List<Consumer<GatewayConfigurator>> BEFORE_INITIALIZATION_CONSUMERS = new ArrayList<>();

  private static final String[] STATIC_CONFIG_PATHS = {
      "gateway.json",
      "resources/gateway.json",
      "src/main/resources/gateway.json",
      "gateway.yaml",
      "gateway.yml",
      "resources/gateway.yaml",
      "resources/gateway.yml",
      "src/main/resources/gateway.yaml",
      "src/main/resources/gateway.yml",
  };

  private static final HashMap<String, String> STATIC_ENVIRONMENT_MAP = new HashMap<String, String>() {
    {
      put("int", "integration");
      put("sand", "sandbox");
    }
  };

  /**
   * Ordered list of possible paths to the gateway configuration file.
   */
  private static String[] configPaths = STATIC_CONFIG_PATHS;

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringMdxGatewayManager.class);
  private static Map<String, Gateway> gateways = null;
  private static String profiles;

  private static boolean initialized = false;
  private static Consumer<GatewayConfigurationFileContext> gatewayFileContentPreProcessor;
  private static GatewayConfigurator configurator = new GatewayConfigurator();

  public static ObjectMap describe() {
    ObjectMap description = new ObjectMap();
    gateways.forEach((clientId, gateway) -> {
      gateway.describe(description.createMap(clientId));
    });

    return description;
  }

  public static List<String> getActiveProfiles() {
    return Arrays.asList(profiles.split(","));
  }

  public static Gateway getClientGateway(String clientId) {
    if (gateways == null) {
      throw new MdxWebApplicationException("Configuration error. Gateway configuration is incomplete");
    }

    return gateways.get(clientId);
  }

  public static ConfiguratorObserver<Gateway> getConfiguratorObserver() {
    return configurator.getObserver();
  }

  /**
   * Register a block of code to run after the Gateways and Facilities are initialized
   *
   * @param consumer block of code to execute
   * @throws MdxWebApplicationException if attempting to register a new consumer after initialization has already occurred.
   *
   * @deprecated Use {@link #getConfiguratorObserver()} to register consumer. Will be removed in next major version
   */
  @Deprecated
  public static void registerAfterInitialized(BiConsumer<Configurator<Gateway>, Map<String, Gateway>> consumer) {
    if (initialized) {
      throw new MdxWebApplicationException("Initialization order issue. Calling registerAfterInitialized after gateways already initialized.");
    }

    getConfiguratorObserver().registerGatewaysInitialized(consumer);
  }

  /**
   * Register listener object annotated with {@link com.google.common.eventbus.Subscribe} annotations
   *
   * @param listener listener object
   *
   * @deprecated Use {@link #getConfiguratorObserver()} to register listeners. Will be removed in next major version
   */
  @Deprecated
  public static void registerAfterInitializedListener(Object listener) {
    if (initialized) {
      throw new MdxWebApplicationException("Initialization order issue. Calling registerListener after gateways already initialized.");
    }

    getConfiguratorObserver().registerListener(listener);
  }

  /**
   * Register consumer that will be fired after Spring context is loaded and before GatewayConfigurator is initiated.
   *
   * @param consumer
   */
  public static void registerBeforeInitialization(Consumer<GatewayConfigurator> consumer) {
    BEFORE_INITIALIZATION_CONSUMERS.add(consumer);
  }

  /**
   * Register block of code that is called after the gateway file contents are located and loaded, but before they are
   * processed by the configurator.
   *
   * <p>This can be used to apply pre-processing to the content of the gateway configuration file.
   *
   * @param gatewayConfigurationFileContextConsumer consumer block
   */
  public static void registerGatewayFileContentPreprocessor(Consumer<GatewayConfigurationFileContext> gatewayConfigurationFileContextConsumer) {
    gatewayFileContentPreProcessor = gatewayConfigurationFileContextConsumer;
  }

  /**
   * FOR TESTING PURPOSES ONLY
   *
   * <p>This class is loaded by Spring. If reset is called on it, there is no way for Spring to know it needs to be reinitialized.
   */
  public static void reset() {
    configurator = new GatewayConfigurator();
    gateways = null;
    profiles = null;
    initialized = false;
    setConfigPaths(STATIC_CONFIG_PATHS);
    gatewayFileContentPreProcessor = null;
    BEFORE_INITIALIZATION_CONSUMERS.clear();
  }

  /**
   * Use {@link #reset()}
   */
  @Deprecated
  public static void resetAfterInitialized() {
    initialized = false;
  }

  @SuppressFBWarnings("EI_EXPOSE_STATIC_REP2")
  public static void setConfigPaths(String[] configPaths) {
    SpringMdxGatewayManager.configPaths = configPaths;
  }

  @Value("${spring.profiles.active}")
  public final void setActiveProfiles(String activeProfiles) {
    profiles = activeProfiles;
    initializeFromProfiles();
  }

  /**
   * Finds the gateway file in given possible locations.
   *
   * @return Path to gateway file or NULL
   */
  public final Path configPath() {
    return Arrays.stream(configPaths).sequential().map(Paths::get).filter(Files::exists).findFirst().orElse(null);
  }

  public final List<Path> compiledConfigPaths() {
    AtomicBoolean baseFound = new AtomicBoolean(false);
    List<Path> returnPaths = new ArrayList<>();

    getActiveProfiles().forEach(profile -> {
      String profileKey = STATIC_ENVIRONMENT_MAP.getOrDefault(profile, profile);
      String basePath = "environments/" + profileKey + ".gateway";
      String partialPath = "environments/partial." + profileKey + ".gateway";

      Path baseConfig = Arrays.stream(configPaths).sequential()
          .filter(p -> p.lastIndexOf("gateway") >= 0)
          .map(p -> p.replace("gateway", basePath))
          .map(Paths::get).filter(Files::exists).findFirst().orElse(null);

      Path partial = Arrays.stream(configPaths).sequential()
          .filter(p -> p.lastIndexOf("gateway") >= 0)
          .map(p -> p.replace("gateway", partialPath))
          .map(Paths::get).filter(Files::exists).findFirst().orElse(null);

      if (baseConfig != null) {
        baseFound.set(true);
        returnPaths.add(baseConfig);
      }

      if (partial != null) {
        returnPaths.add(partial);
      }
    });

    // If no base config is found, fall back to non-compiled
    if (!baseFound.get()) {
      return new ArrayList<>();
    }

    return returnPaths;
  }

  private void initializeFromProfiles() {
    // Notify consumers that initialization is starting
    BEFORE_INITIALIZATION_CONSUMERS.forEach(consumer -> consumer.accept(configurator));

    GatewayConfigurationFileContext gatewayConfigurationFileContext = buildConfigFile();

    // Hand off configuration file to pre-processor block if it is present
    if (gatewayFileContentPreProcessor != null) {
      gatewayFileContentPreProcessor.accept(gatewayConfigurationFileContext);
    }

    if (gatewayConfigurationFileContext.isJson()) {
      gateways = configurator.buildFromJson(gatewayConfigurationFileContext.contents);
    } else if (gatewayConfigurationFileContext.isYaml()) {
      gateways = configurator.buildFromYaml(gatewayConfigurationFileContext.contents);
    } else {
      throw new RuntimeException("Invalid gateway configuration format: must be JSON or YAML");
    }

    gateways.forEach((clientId, gateway) -> {
      LOGGER.info("Starting service for " + clientId);
      gateway.startServices();
      LOGGER.info("Registering remotes for " + clientId);
      gateway.registerRemotes();
    });

    initialized = true;
  }

  private GatewayConfigurationFileContext buildConfigFile() {
    Path configPath = configPath();
    List<Path> compiledConfigPaths = compiledConfigPaths();
    boolean compiledExists = !compiledConfigPaths.isEmpty();

    if (configPath == null && !compiledExists) {
      throw new RuntimeException("Gateway configuration file does not exist");
    }

    if (compiledExists) {
      compiledConfigPaths.forEach(path -> LOGGER.info("COMPILED: Loading gateway configuration from " + path));
    } else {
      LOGGER.info("Loading gateway configuration from " + configPath);
    }

    String fileContents = compiledExists ? buildMergedConfigString(compiledConfigPaths) : readConfigFile(configPath);
    String path = compiledExists ? compiledConfigPaths.get(0).toString() : configPath.toString();

    return new GatewayConfigurationFileContext(fileContents, path, getActiveProfiles(), SpringMdxGatewayManager.configurator);
  }

  private String buildMergedConfigString(List<Path> paths) {
    List<Resource> resources = new ArrayList<>();
    ObjectMapYamlDeserializer yamlSerializer = new ObjectMapYamlDeserializer();
    YamlMapFactoryBean factory = new YamlMapFactoryBean();
    factory.setResolutionMethod(YamlProcessor.ResolutionMethod.OVERRIDE_AND_IGNORE);

    paths.forEach(path -> resources.add(new FileSystemResource(path.toString())));

    factory.setResources(resources.toArray(new Resource[0]));
    Map<String, Object> yamlValueMap = factory.getObject();

    return yamlSerializer.toYaml(yamlValueMap);
  }

  private String readConfigFile(Path configPath) {
    String fileContents;
    try {
      fileContents = new String(Files.readAllBytes(configPath), StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new RuntimeException("Unable to read " + configPath, e);
    }
    return fileContents;
  }

  public static class GatewayConfigurationFileContext {
    @Getter
    @Setter
    private String contents;
    @Getter
    private final String path;
    @Getter
    private final List<String> activeProfiles;
    @Getter
    private final GatewayConfigurator configurator;

    GatewayConfigurationFileContext(String contents, String path, List<String> activeProfile, GatewayConfigurator configurator) {
      this.contents = contents;
      this.path = path;
      this.activeProfiles = activeProfile;
      this.configurator = configurator;
    }

    public final boolean isYaml() {
      return this.path.endsWith(".yaml") || this.path.endsWith(".yml");
    }

    public final boolean isJson() {
      return this.path.endsWith(".json");
    }
  }
}
