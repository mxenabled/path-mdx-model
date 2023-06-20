package com.mx.path.model.mdx.web.controller;

import java.util.List;
import java.util.Map;

import com.mx.path.core.common.accessor.PathResponseStatus;
import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.context.RequestContext;
import com.mx.path.gateway.api.Gateway;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class BaseController {
  // NO INSTANCE VARIABLES! Controllers are Singletons

  public static final String MDX_MEDIA = "application/vnd.mx.mdx.v6+json;charset=UTF-8";
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

  public final MultiValueMap<String, String> createMultiMapForResponse(Map<String, String> accessorHeaderMap) {
    MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
    accessorHeaderMap.entrySet().stream().forEach(entry -> headerMap.add(entry.getKey(), entry.getValue()));
    return headerMap;
  }

  public final MultiValueMap<String, String> createMultiMapForResponse(Map<String, String> accessorHeaderMap,
      HttpHeaders httpHeaders) {
    MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<>();
    accessorHeaderMap.entrySet().stream().forEach(entry -> headerMap.add(entry.getKey(), entry.getValue()));
    headerMap.addAll(httpHeaders);
    return headerMap;
  }
}
