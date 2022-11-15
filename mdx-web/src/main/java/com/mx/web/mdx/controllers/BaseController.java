package com.mx.web.mdx.controllers;

import java.util.List;
import java.util.Map;

import com.mx.common.accessors.PathResponseStatus;
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

  public static void clearRepository() {
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
