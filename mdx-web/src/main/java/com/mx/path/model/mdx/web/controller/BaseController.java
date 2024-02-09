package com.mx.path.model.mdx.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.core.common.accessor.PathResponseStatus;
import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.common.serialization.PathRequestSerializableException;
import com.mx.path.core.context.RequestContext;
import com.mx.path.core.context.ResponseContext;
import com.mx.path.gateway.api.Gateway;

import com.mx.path.model.mdx.model.Resources;
import com.mx.path.model.mdx.web.VersionedResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;

@Service
public class BaseController {
  // NO INSTANCE VARIABLES! Controllers are Singletons

  public static final String MDX_MEDIA = "application/vnd.mx.mdx.v6+json;version=0;charset=UTF-8";
  public static final String MDX_MEDIA_1 = "application/vnd.mx.mdx.v6+json;version=1;charset=UTF-8";
  public static final String MDX_ONDEMAND_MEDIA = "application/vnd.moneydesktop.mdx.v5+xml";

  private static ThreadLocal<Gateway> gatewayThreadLocal = new ThreadLocal<>();

  public static void clearGateway() {
    gatewayThreadLocal.remove();
  }

  public static Gateway gateway() {
    return gatewayThreadLocal.get();
  }

  public static void setGateway(Gateway gateway) {
    gatewayThreadLocal.set(gateway);
  }

  protected final String getSessionId(HttpHeaders headers) {
    String sessionId = null;

    if (headers != null) {
      List<String> sessionHeaders = headers.get("MX-SESSION-KEY");
      if (sessionHeaders != null && sessionHeaders.size() > 0) {
        sessionId = sessionHeaders.get(0);
      }
    }

    return sessionId;
  }

  protected final VersionedResponse versioned(HttpServletRequest request) {
    return new VersionedResponse(request);
  }

  /**
   * In cases where the downstream system is not expected to send the request feature, call this to ensure one is set.
   *
   * @param feature to use for request
   */
  protected void ensureFeature(String feature) {
    if (RequestContext.current() != null && Strings.isBlank(RequestContext.current().getFeature())) {
      RequestContext.current().setFeature(feature);
    }
  }

  protected final HttpStatus statusFromAccessorStatus(PathResponseStatus accessorResponseStatus) {
    return HttpStatus.resolve(accessorResponseStatus.value());
  }

  public final MultiValueMap<String, String> createMultiMapForResponse(Map<String, String> accessorResponseHeaders) {
    MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();

    // Attach headers from ResponseContext
    attachResponseContextHeaders(headerMap);

    // Attach headers added by the controller (these take precedence)
    accessorResponseHeaders.forEach(headerMap::set);

    return headerMap;
  }

  public final MultiValueMap<String, String> createMultiMapForResponse(Map<String, String> accessorResponseHeaders,
      HttpHeaders httpHeaders) {
    MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();

    // Attach headers from ResponseContext
    attachResponseContextHeaders(headerMap);

    // Attach headers from accessor response
    accessorResponseHeaders.forEach(headerMap::set);

    // Attach headers added by the controller (these take precedence)
    headerMap.addAll(httpHeaders);

    return headerMap;
  }

  public final void attachResponseContextHeaders(MultiValueMap<String, String> headerMap) {
    if (ResponseContext.current() != null && ResponseContext.current().getHeaders() != null) {
      ResponseContext.current().getHeaders().forEach(headerMap::set);
    }
  }
}
