package com.mx.path.service.splunk;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.core.common.connect.Request;
import com.mx.path.core.common.connect.Response;
import com.mx.path.core.context.RequestContext;
import com.mx.path.core.context.ScopeKeyGenerator;
import com.mx.path.core.context.Session;
import com.mx.path.gateway.util.LoggingExceptionFormatter;

import org.slf4j.MDC;

@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.CyclomaticComplexity" })
public class SplunkLog {

  private static final SplunkLogMasker LOGMASKER = new SplunkLogMasker();
  private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

  private @Getter String accountId;
  private @Getter String clientId;
  private @Getter String deviceId;
  private @Getter String deviceIsJailbroken;
  private @Getter String deviceMake;
  private @Getter String deviceName;
  private @Getter String deviceOs;
  private @Getter String deviceModel;
  private @Getter String exception;
  private @Getter String feature;
  private @Getter String ipAddress;
  private @Getter String path;
  private @Getter String requestBody;
  private @Getter String requestDuration;
  private @Getter String requestHeaders;
  private @Getter String requestMethod;
  private @Getter String requestUri;
  private @Getter String responseBody;
  private @Getter String responseHeaders;
  private @Getter String sessionId;
  private @Getter String status;
  private @Getter String traceId;
  private @Getter String userId;

  /**
   * For backward compatibility
   */
  public enum CorillianServiceIdentifier implements ScopeKeyGenerator {
    Corillian {
      @Override
      public String generate() {
        return "Corillian";
      }
    }
  }

  @SuppressWarnings("unchecked")
  public SplunkLog(Response<?, ?> response) {
    Session session = Session.current();
    Map<String, String> splunkSessionAttributes = session.getObj(CorillianServiceIdentifier.Corillian, "splunkSessionAttributes", HashMap.class);
    accountId = splunkSessionAttributes.getOrDefault("accountId", "");
    deviceId = splunkSessionAttributes.getOrDefault("deviceId", "");
    deviceIsJailbroken = splunkSessionAttributes.getOrDefault("deviceIsJailbroken", "");
    deviceMake = splunkSessionAttributes.getOrDefault("deviceMake", "");
    deviceModel = splunkSessionAttributes.getOrDefault("deviceModel", "");
    deviceName = splunkSessionAttributes.getOrDefault("deviceName", "");
    deviceOs = splunkSessionAttributes.getOrDefault("deviceOs", "");
    sessionId = splunkSessionAttributes.getOrDefault("sessionId", "");
    Request<?, ?> request = response.getRequest();
    requestMethod = request.getMethod();
    requestUri = request.getUri();
    traceId = request.getTraceId();

    RequestContext requestContext = RequestContext.current();
    clientId = requestContext.getClientId();
    feature = requestContext.getFeature();
    ipAddress = requestContext.getOriginatingIP();
    path = requestContext.getPath();
    userId = requestContext.getUserId();

    if (request.getBody() != null) {
      requestBody = LOGMASKER.maskPayload(request.getBody().toString());
    }
    if (response.getDuration() != null) {
      requestDuration = String.valueOf(response.getDuration());
    }
    if (request.getHeaders() != null) {
      requestHeaders = buildHeaderString(maskHeaders(request.getHeaders()));
    }

    if (response.getBody() != null) {
      responseBody = LOGMASKER.maskPayload(response.getBody());
    }
    if (response.getHeaders() != null) {
      responseHeaders = buildHeaderString(maskHeaders(response.getHeaders()));
    }
    if (response.getStatus() != null) {
      status = String.valueOf(response.getStatus().value());
    }

    if (response.getException() != null) {
      this.exception = LoggingExceptionFormatter.formatLoggingException(response.getException());
      MDC.put("exception", exception);
    } else {
      MDC.remove("exception");
    }

    //Remove MDC leftovers
    MDC.remove("client_id");
    MDC.remove("exception");
    MDC.remove("is_circuit_breaker_open");
    MDC.remove("path");
    MDC.remove("query_params");
    MDC.remove("parentId");
    MDC.remove("spanExportable");
    MDC.remove("spanId");
    MDC.remove("traceId");
    MDC.remove("X-B3-ParentSpanId");
    MDC.remove("X-B3-SpanId");
    MDC.remove("X-B3-TraceId");
    MDC.remove("X-Span-Export");
  }

  private String buildHeaderString(Map<String, String> headers) {
    if (headers == null) {
      return null;
    }
    StringBuilder headerStr = new StringBuilder();
    headers.forEach((name, value) -> {
      headerStr.append(name);
      headerStr.append(": ");
      headerStr.append(value);
      headerStr.append("\n");
    });

    return headerStr.toString();
  }

  private Map<String, String> maskHeaders(Map<String, String> headers) {
    if (headers == null) {
      return null;
    }
    Map<String, String> maskedHeaders = new HashMap<>();
    headers.forEach((name, value) -> maskedHeaders.put(name, LOGMASKER.maskHeaderValue(name, value)));
    return maskedHeaders;
  }

  @Override
  public final String toString() {
    return GSON.toJson(this);
  }
}
