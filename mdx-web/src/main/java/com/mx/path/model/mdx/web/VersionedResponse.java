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

import javax.servlet.http.HttpServletRequest;

import lombok.Builder;
import lombok.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.common.serialization.PathRequestSerializableException;
import com.mx.path.model.mdx.model.Resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class VersionedResponse {
  private final HttpServletRequest request;
  private final Map<Integer, VersionedResponder> responders = new HashMap<>();
  private VersionedResponder defaultVersion;

  @Data
  @Builder
  public static class VersionedResponder {
    private Function<Object, ResponseEntity<?>> supplier;
    private Integer version;
    private Class<?> requestType;
    private Class<?> responseType;

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
          throw new PathRequestSerializableException();
        }
      }

      ResponseEntity<T> response = (ResponseEntity<T>) supplier.apply(requestObject);

      String contentType;
      if (version == null) {
        contentType = MDX_MEDIA;
      } else {
        // todo: figure out a better way to inject version
        contentType = MDX_MEDIA.replace("+json;", "+json;version=" + version + ";");
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

  public VersionedResponse(HttpServletRequest request) {
    this.request = request;
  }

  public final <REQ> VersionedResponse version(int version, Class<REQ> requestType, Class<?> responseType, Function<REQ, ResponseEntity<?>> supplier) {
    responders.put(version, VersionedResponder.builder()
        .requestType(requestType)
        .responseType(responseType)
        .version(version)
        .supplier((Function<Object, ResponseEntity<?>>) supplier)
        .build());

    return this;
  }

  public final <REQ> VersionedResponse defaultVersion(Class<REQ> requestType, Class<?> responseType, Function<REQ, ResponseEntity<?>> supplier) {
    this.defaultVersion = VersionedResponder.builder()
        .requestType(requestType)
        .responseType(responseType)
        .supplier((Function<Object, ResponseEntity<?>>) supplier)
        .build();

    return this;
  }

  public final ResponseEntity<?> execute() {
    Integer version = extractVersion("Accept");
    if (version == null) {
      version = extractVersion("Content-Type");
    }

    return supplier(version).call(request);
  }

  private Integer extractVersion(String header) {
    // todo: figure out a better way to extract version
    String patternString = ";version=(\\d+)";
    Pattern p = Pattern.compile(patternString);

    Enumeration<String> headers = request.getHeaders(header);
    while (headers.hasMoreElements()) {
      String value = headers.nextElement();
      Matcher m = p.matcher(value);

      if (m.find()) {
        return fixVersion(Integer.valueOf(m.group(1)));
      }
    }

    return null;
  }

  private Integer fixVersion(Integer version) {
    return new ArrayList<>(responders.keySet())
        .stream()
        .filter(i -> i <= version)
        .max(Integer::compareTo)
        .orElseGet(() -> null);
  }

  private VersionedResponder supplier(Integer version) {
    if (version == null) {
      return defaultVersion;
    }

    if (responders.containsKey(version)) {
      return responders.get(version);
    }

    return defaultVersion;
  }
}
