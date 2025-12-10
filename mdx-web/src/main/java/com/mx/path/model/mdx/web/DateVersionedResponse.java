package com.mx.path.model.mdx.web;

import static com.mx.path.model.mdx.web.controller.BaseController.MDX_MEDIA;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.core.common.accessor.PathResponseStatus;
import com.mx.path.core.common.accessor.RequestValidationException;
import com.mx.path.core.common.configuration.ConfigurationException;
import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.common.serialization.PathRequestSerializableException;
import com.mx.path.model.mdx.model.Resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Use in Controller to provide multiple code paths, based on provided version header.
 * <p>
 * The Version header is expected to be present in the Accept or Content-Type header as a parameter.
 * <p>
 * <b>Example:</b>
 * <pre>{@code
 * Accept: application/json;version=20240101
 * }</pre>
 * <p>
 * <b>Usage:</b>
 * <pre>{@code
 * return new DateVersionedResponse(request)
 *   .defaultVersion(Account, Account, { o ->
 *     // Insert normal controller code for default version
 *     return ResponseEntity.ok().build()
 *   })
 *   .version(20240323, com.mx.path.model.mdx.model.account.v20240323.Account, com.mx.path.model.mdx.model.account.v20240323.Account, { o ->
 *     // Insert normal controller code for version 20240323
 *     return ResponseEntity.ok().build()
 *   })
 *   .execute()
 * }</pre>
 */
public class DateVersionedResponse {
  private static final int MINIMUM_VERSION = 19700101;
  private static final Pattern VERSION_PATTERN = Pattern.compile(";\\s*version=([^;]+)");
  /**
   * Executed when no matching version found or no version provided
   */
  private DateVersionedResponder defaultVersion;
  /**
   * Incoming request from Controller
   */
  private final HttpServletRequest request;
  /**
   * Responders for each version
   */
  private final Map<Integer, DateVersionedResponder> responders = new HashMap<>();

  @Data
  @Builder
  static class DateVersionedResponder {
    private Class<?> requestType;
    private Class<?> responseType;
    private Function<Object, ResponseEntity<?>> supplier;
    private Integer version;

    @SuppressWarnings("unchecked")
    public final <T> ResponseEntity<T> call(HttpServletRequest request) {
      GsonBuilder builder = new GsonBuilder();
      Resources.registerResources(builder);

      Gson gson = builder.create();
      String requestBody = getRequestBody(request);

      Object requestObject = null;

      if (Strings.isNotBlank(requestBody)) {
        try {
          requestObject = gson.fromJson(requestBody, requestType);
        } catch (Exception e) {
          throw new WebPathRequestSerializationException(requestType.getTypeName(), e);
        }
      }

      ResponseEntity<T> response = (ResponseEntity<T>) supplier.apply(requestObject);

      String contentType;
      if (version == null) {
        contentType = MDX_MEDIA;
      } else {
        contentType = MDX_MEDIA + ";version=" + version;
      }

      HttpHeaders headers = new HttpHeaders();
      headers.addAll(response.getHeaders());
      headers.add("Content-Type", contentType);

      // Add Content-Type header to response
      return new ResponseEntity<>(response.getBody(), headers, response.getStatusCode());
    }

    private String getRequestBody(HttpServletRequest request) {
      try (BufferedReader reader = request.getReader()) {
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      } catch (IOException e) {
        throw new PathRequestSerializableException();
      }
    }
  }

  public DateVersionedResponse(HttpServletRequest request) {
    this.request = request;
  }

  /**
   * Default responder
   * <p>
   * Only one default may be provided. Only the last one will take effect.
   *
   * @param requestType
   * @param responseType
   * @param supplier
   * @param <REQ>
   * @return result from code block
   */
  @SuppressWarnings("unchecked")
  public final <REQ> DateVersionedResponse defaultVersion(Class<REQ> requestType, Class<?> responseType, Function<REQ, ResponseEntity<?>> supplier) {
    this.defaultVersion = DateVersionedResponder.builder()
        .requestType(requestType)
        .responseType(responseType)
        .supplier((Function<Object, ResponseEntity<?>>) supplier)
        .build();

    return this;
  }

  /**
   * Call to execute the incoming request
   *
   * @return ResponseEntity from code block
   */
  public final ResponseEntity<?> execute() {
    Integer acceptVersion = extractVersionFromHeader("Accept");
    Integer contentTypeVersion = extractVersionFromHeader("Content-Type");

    // If both version are present and versions are not the same, throw exception
    if (acceptVersion != null
        && contentTypeVersion != null
        && !acceptVersion.equals(contentTypeVersion)) {
      throw new RequestValidationException("Accept and Content-Type versions must match.", "Accept and Content-Type versions must match.").withStatus(PathResponseStatus.BAD_REQUEST);
    }

    Integer version = acceptVersion;
    if (version == null) {
      version = contentTypeVersion;
    }
    version = pickVersion(version);

    return supplier(version).call(request);
  }

  /**
   * Responder for provided version
   *
   * @param version      version in YYYYMMDD format
   * @param requestType  incoming object type (used to deserialize request)
   * @param responseType response object type
   * @param supplier     code block
   * @param <REQ>
   * @return result from code block
   */
  public final <REQ> DateVersionedResponse version(int version, Class<REQ> requestType, Class<?> responseType, Function<REQ, ResponseEntity<?>> supplier) {
    if (version < MINIMUM_VERSION) {
      throw new ConfigurationException("Invalid version specified in controller. Must be a numeric date in the format YYYYMMDD and must be greater than " + MINIMUM_VERSION + ".");
    }

    responders.put(version, DateVersionedResponder.builder()
        .requestType(requestType)
        .responseType(responseType)
        .version(version)
        .supplier((Function<Object, ResponseEntity<?>>) supplier)
        .build());

    return this;
  }

  /**
   * Extracts the version from provided header.
   *
   * @param header
   * @return responder version
   */
  private Integer extractVersionFromHeader(String header) {
    Enumeration<String> headers = request.getHeaders(header);
    while (headers.hasMoreElements()) {
      String value = headers.nextElement();
      Matcher m = VERSION_PATTERN.matcher(value);

      if (m.find()) {
        try {
          return Integer.valueOf(m.group(1));
        } catch (NumberFormatException e) {
          throw new RequestValidationException("Invalid version specified in " + header + " header", "Invalid version specified in " + header + " header", e).withStatus(PathResponseStatus.BAD_REQUEST);
        }
      }
    }

    return null;
  }

  /**
   * Takes provided version and selects the responder version to use.
   *
   * @param version Provided version
   * @return version of the selected responder
   */
  private Integer pickVersion(Integer version) {
    if (version == null) {
      return null;
    }

    return new ArrayList<>(responders.keySet())
        .stream()
        .filter(i -> i <= version)
        .max(Integer::compareTo)
        .orElseGet(() -> null);
  }

  /**
   * Select the supplier for provided version.
   *
   * @param version
   * @return
   */
  private DateVersionedResponder supplier(Integer version) {
    if (version == null) {
      return defaultVersion;
    }

    if (responders.containsKey(version)) {
      return responders.get(version);
    }

    return defaultVersion;
  }
}
