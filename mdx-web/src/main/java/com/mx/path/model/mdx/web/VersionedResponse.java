package com.mx.path.model.mdx.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.common.serialization.PathRequestSerializableException;
import com.mx.path.model.mdx.model.Resources;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.mx.path.model.mdx.web.controller.BaseController.MDX_MEDIA;

public class VersionedResponse {
  private final HttpServletRequest request;
  private final Map<Integer, VersionedResponder> responders = new HashMap<>();
  private VersionedResponder defaultVersion;

  @Data
  @Builder
  public static class VersionedResponder {
    private Function<Object, ResponseEntity<?>> supplier;
    private Integer minorVersion;
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
      response.getHeaders().add("Content-Type", MDX_MEDIA.replace("+json;", ";v=" + minorVersion + ";"));

      return response;
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

  public final <REQ> VersionedResponse version(int minorVersion, Class<REQ> requestType, Class<?> responseType, Function<REQ, ResponseEntity<?>> supplier) {
    responders.put(minorVersion, VersionedResponder.builder()
        .requestType(requestType)
        .responseType(responseType)
        .minorVersion(minorVersion)
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
    Integer minorVersion = extractMinorVersion("Accept");
    if (minorVersion == null) {
      minorVersion = extractMinorVersion("Content-Type");
    }

    return supplier(minorVersion).call(request);
  }

  private Integer extractMinorVersion(String header) {
    String patternString = ";v=(\\d+)";
    Pattern p = Pattern.compile(patternString);

    Enumeration<String> headers = request.getHeaders(header);
    while (headers.hasMoreElements()) {
      String value = headers.nextElement();
      Matcher m = p.matcher(value);

      if (m.find()) {
        return Integer.valueOf(m.group(1));
      }
    }

    return null;
  }

  private VersionedResponder supplier(Integer minorVersion) {
    if (minorVersion == null) {
      return defaultVersion;
    }
    if (responders.containsKey(minorVersion)) {
      return responders.get(minorVersion);
    }
    return defaultVersion;
  }
}
