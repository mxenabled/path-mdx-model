package com.mx.path.model.mdx.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mx.path.model.mdx.model.Resources;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.http.converter.xml.JacksonXmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.dataformat.xml.XmlMapper;

@Configuration
public class MdxOnDemandSerializationWebMvcConfigurer implements WebMvcConfigurer {

  /**
   * Converters retained by {@link #extendMessageConverters(List)}; every other converter is removed before the
   * custom XML converter is appended. Gson handles MDX JSON serialization. The Jackson JSON converter is retained
   * because Spring Boot's actuator endpoints (e.g. {@code /actuator/health}) are serialized with Jackson; without it
   * those responses have no writable converter and fail with {@code HttpMessageNotWritableException}.
   */
  static final List<Class<?>> CONVERT_CLASSES;
  static {
    CONVERT_CLASSES = new ArrayList<>();
    CONVERT_CLASSES.add(GsonHttpMessageConverter.class);
    CONVERT_CLASSES.add(StringHttpMessageConverter.class);
    CONVERT_CLASSES.add(ByteArrayHttpMessageConverter.class);
    CONVERT_CLASSES.add(JacksonJsonHttpMessageConverter.class);
  }

  @Override
  public final void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    // Filter out unwanted default converters to strictly enforce custom serialization
    List<HttpMessageConverter<?>> toRemove = converters.stream().filter(t -> !CONVERT_CLASSES.contains(t.getClass())).collect(Collectors.toCollection(ArrayList::new));
    converters.removeAll(toRemove);

    // XML
    converters.add(new JacksonXmlHttpMessageConverter(XmlMapper.builder()
        .addModule(payloadModule())
        .enable(SerializationFeature.INDENT_OUTPUT)
        .build()));
  }

  public final SimpleModule payloadModule() {
    SimpleModule module = new SimpleModule();

    Resources.registerOnDemandResources(module);

    return module;
  }
}
