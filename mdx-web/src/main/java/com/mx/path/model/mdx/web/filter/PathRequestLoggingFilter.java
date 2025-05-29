package com.mx.path.model.mdx.web.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.core.context.RequestContext;
import com.mx.path.gateway.util.LoggingExceptionFormatter;
import com.mx.path.model.mdx.model.MdxLogMasker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * Request logging filter
 * <p>
 * This filter logs incoming requests.
 * These values are inserted into the MDC.
 */
@Component
@Order(FilterOrderSequence.REQUEST_LOGGING_FILTER)
public class PathRequestLoggingFilter extends OncePerRequestFilter {

  // Statics
  private static final Gson GSON;
  private static Logger logger;

  public static void setLogger(Logger logger) {
    PathRequestLoggingFilter.logger = logger;
  }

  public static void resetLogger() {
    logger = LoggerFactory.getLogger(PathRequestLoggingFilter.class);
  }

  // Protected
  @Override
  protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    ContentCachingRequestWrapper requestWrapper;
    ContentCachingResponseWrapper responseWrapper;

    try {
      requestWrapper = new ContentCachingRequestWrapper(request);
      responseWrapper = new ContentCachingResponseWrapper(response);
    } catch (Exception ex) {
      MDC.put("exception", LoggingExceptionFormatter.formatLoggingExceptionWithStacktrace(ex));
      logger.error("Failed to create request/response wrappers for logging purposes.  Passing request through.", ex);
      filterChain.doFilter(request, response);
      return;
    }

    final long requestStartTimeMillis = System.currentTimeMillis();

    try {
      // Pass request to next filter with wrappers to support logging request and response bodies
      filterChain.doFilter(requestWrapper, responseWrapper);
    } finally {
      if (!request.getRequestURI().contains("/status")) {
        try {
          this.logRequest(requestWrapper, responseWrapper, requestStartTimeMillis);
        } catch (Exception ex) {
          MDC.put("exception", LoggingExceptionFormatter.formatLoggingExceptionWithStacktrace(ex));
          logger.error("Failed to log incoming request", ex);
        } finally {
          resetMDC();
        }
      }

      responseWrapper.copyBodyToResponse();
    }
  }

  /**
   * logs request/response via logback appender. Function is protected for testing purposes, do not extend
   * @param request
   * @param response
   * @param requestStartTimeMillis
   * @throws IOException
   */
  @SuppressWarnings("PMD.CyclomaticComplexity")
  protected void logRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response,
      long requestStartTimeMillis) throws IOException {
    final long elapsedTime = System.currentTimeMillis() - requestStartTimeMillis;
    final RequestContext requestContext = RequestContext.current();

    MDC.put("log_guid", UUID.randomUUID().toString());
    MDC.put("client_guid", requestContext.getClientGuid());
    MDC.put("client_id", requestContext.getClientId());
    MDC.put("path", requestContext.getPath());

    if (requestContext.getUserGuid() != null) {
      MDC.put("user_guid", requestContext.getUserGuid());
    } else {
      MDC.remove("user_guid");
    }

    if (requestContext.getFeature() != null) {
      MDC.put("feature", requestContext.getFeature());
    } else {
      MDC.remove("feature");
    }

    if (requestContext.getOriginatingIP() != null) {
      MDC.put("ip_address", requestContext.getOriginatingIP());
    } else {
      MDC.remove("ip_address");
    }

    if (requestContext.getSessionTraceId() != null) {
      MDC.put("session_trace_id", requestContext.getSessionTraceId());
    } else {
      MDC.remove("session_trace_id");
    }

    if (requestContext.getDeviceTraceId() != null) {
      MDC.put("device_trace_id", requestContext.getDeviceTraceId());
    } else {
      MDC.remove("device_trace_id");
    }

    MDC.put("request_method", request.getMethod());
    MDC.put("request_uri", String.valueOf(request.getRequestURL()));

    if (request.getQueryString() != null) {
      final Map<String, String> queryParams = this.buildQueryStringMap(request.getQueryString());
      MDC.put("query_params", this.buildHeaderString(this.maskHeaders(queryParams)));
    } else {
      MDC.remove("query_params");
    }

    if (request.getHeaderNames() != null) {
      final Map<String, String> requestHeaders = this.buildRequestHeadersMap(request);
      final Map<String, String> maskedRequestHeaders = this.maskHeaders(requestHeaders);
      MDC.put("request_headers_json", GSON.toJson(maskedRequestHeaders));
      MDC.put("request_headers", this.buildHeaderString(maskedRequestHeaders));
    } else {
      MDC.remove("request_headers_json");
      MDC.remove("request_headers");
    }

    final String requestBody = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
    if (!requestBody.isEmpty()) {
      MDC.put("request_body", MdxLogMasker.maskPayload(requestBody));
    } else {
      MDC.remove("request_body");
    }

    MDC.put("api_call_payload", buildApiPayload(response, request));
    MDC.put("request_duration", String.valueOf(elapsedTime));
    MDC.put("status", String.valueOf(response.getStatus()));

    if (response.getHeaderNames() != null) {
      final Map<String, String> responseHeaders = this.buildResponseHeadersMap(response);
      final Map<String, String> maskedResponseHeaders = this.maskHeaders(responseHeaders);

      if (response.getContentType() != null) {
        maskedResponseHeaders.put("Content-Type", response.getContentType());
      }

      if (!maskedResponseHeaders.isEmpty()) {
        MDC.put("response_headers_json", GSON.toJson(maskedResponseHeaders));
        MDC.put("response_headers", this.buildHeaderString(maskedResponseHeaders));
      }
    } else {
      MDC.remove("response_headers_json");
      MDC.remove("response_headers");
    }

    final String responseBody = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
    if (!responseBody.isEmpty()) {
      MDC.put("response_body", MdxLogMasker.maskPayload(responseBody));
    } else {
      MDC.remove("response_body");
    }

    logger.info("Incoming request");
  }

  /**
   * Resets the MDC by just cleaning up what we place there.
   */
  protected void resetMDC() {
    MDC.remove("api_call_payload");
    MDC.remove("client_guid");
    MDC.remove("client_id");
    MDC.remove("device_trace_id");
    MDC.remove("exception");
    MDC.remove("feature");
    MDC.remove("ip_address");
    MDC.remove("log_guid");
    MDC.remove("path");
    MDC.remove("query_params");
    MDC.remove("request_body");
    MDC.remove("request_duration");
    MDC.remove("request_headers");
    MDC.remove("request_headers_json");
    MDC.remove("request_method");
    MDC.remove("response_body");
    MDC.remove("response_headers");
    MDC.remove("response_headers_json");
    MDC.remove("session_trace_id");
    MDC.remove("status");
    MDC.remove("user_guid");
  }

  private String buildApiPayload(ContentCachingResponseWrapper response, ContentCachingRequestWrapper request) {
    StringBuilder b = new StringBuilder();
    b.append("\n= Request\n\n");
    b.append(request.getMethod());
    b.append(" ");
    b.append(request.getRequestURL());
    b.append("\n\n");
    if (request.getHeaderNames() != null) {
      Map<String, String> requestHeaders = this.buildRequestHeadersMap(request);
      Map<String, String> maskedRequestHeaders = this.maskHeaders(requestHeaders);
      b.append(this.buildHeaderString(maskedRequestHeaders));
    }

    String queryString = request.getQueryString();
    if (queryString != null) {
      Map<String, String> queryParams = this.buildQueryStringMap(queryString);
      b.append(this.buildHeaderString(this.maskHeaders(queryParams)));
    }

    String requestBody = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
    if (!requestBody.isEmpty()) {
      b.append("\n");
      b.append(MdxLogMasker.maskPayload(requestBody));
      b.append("\n");
    }

    b.append("\n= Response\n\n");
    b.append(response.getStatus());
    b.append("\n");
    if (response.getHeaderNames() != null) {
      Map<String, String> responseHeaders = this.buildResponseHeadersMap(response);
      Map<String, String> maskedResponseHeaders = this.maskHeaders(responseHeaders);

      if (response.getContentType() != null) {
        maskedResponseHeaders.put("Content-Type", response.getContentType());
      }

      if (!maskedResponseHeaders.isEmpty()) {
        b.append(buildHeaderString(maskedResponseHeaders));
      }
    }

    String responseBody = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
    if (!responseBody.isEmpty()) {
      b.append(MdxLogMasker.maskPayload(responseBody));
    }
    return b.toString();
  }

  private Map<String, String> buildRequestHeadersMap(HttpServletRequest request) {
    final Enumeration<String> headerNames = request.getHeaderNames();
    final Map<String, String> headerMap = new HashMap<>();

    if (headerNames == null) {
      return headerMap;
    }

    while (headerNames.hasMoreElements()) {
      final String headerName = headerNames.nextElement();
      final String headerValue = request.getHeader(headerName);
      headerMap.put(headerName, headerValue);
    }

    return headerMap;
  }

  private Map<String, String> buildResponseHeadersMap(HttpServletResponse response) {
    final Collection<String> headerNames = response.getHeaderNames();
    final Map<String, String> headerMap = new HashMap<>();

    if (headerNames == null) {
      return headerMap;
    }

    for (String headerName : headerNames) {
      final Collection<String> headerValues = response.getHeaders(headerName);

      if (headerValues != null) {
        headerMap.put(headerName, (String) headerValues.toArray()[0]);
      }
    }

    return headerMap;
  }

  private Map<String, String> buildQueryStringMap(String queryString) {
    final Map<String, String> querystringMap = new HashMap<>();
    final String[] parameters = queryString.split("&");

    for (String parameter : parameters) {
      final String[] keyValuePair = parameter.split("=");
      if (keyValuePair.length == 2) {
        querystringMap.put(keyValuePair[0], keyValuePair[1]);
      }
    }

    return querystringMap;
  }

  private String buildHeaderString(Map<String, String> headers) {
    final StringBuilder headerStr = new StringBuilder();
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

    final Map<String, String> maskedHeaders = new HashMap<>();
    headers.forEach((name, value) -> {
      maskedHeaders.put(name, MdxLogMasker.maskHeaderValue(name, value));
    });
    return maskedHeaders;
  }

  static {
    GSON = new GsonBuilder().disableHtmlEscaping().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
    logger = LoggerFactory.getLogger(PathRequestLoggingFilter.class);
  }
}
