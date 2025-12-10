package com.mx.path.model.mdx.web.filter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import com.mx.path.core.common.collection.MultiValueMap;
import com.mx.path.core.common.exception.ExceptionContext;
import com.mx.path.core.context.RequestContext;
import com.mx.path.core.context.Session;

import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

import io.opentracing.util.GlobalTracer;

import jakarta.servlet.http.HttpServletRequest;

class WebExceptionContext implements ExceptionContext {

  private final Map<String, String> context = new LinkedHashMap<>();
  private final Environment environment;
  private final HttpServletRequest request;
  private final RequestContext requestContext;
  private final Session session;
  private long sessionCreateTime;

  WebExceptionContext(HttpServletRequest request, Environment environment, Session session, RequestContext requestContext) {
    this.request = request;
    this.requestContext = requestContext;
    this.session = session;
    this.environment = environment;
  }

  @Override
  public final String getClientId() {
    if (requestContext == null) {
      return null;
    }

    return requestContext.getClientId();
  }

  @Override
  public @NonNull final Map<String, String> getContext() {
    return context;
  }

  @Override
  public final String getEnvironment() {
    return environment.getActiveProfiles()[0];
  }

  @Override
  public final String getFeature() {
    if (requestContext == null) {
      return null;
    }

    return requestContext.getFeature();
  }

  @Override
  public final MultiValueMap<String, String> getHeaders() {
    MultiValueMap<String, String> headers = new MultiValueMap<>();
    Enumeration<String> names = request.getHeaderNames();
    while (names.hasMoreElements()) {
      String headerName = names.nextElement();
      Enumeration<String> headerValues = request.getHeaders(headerName);
      while (headerValues.hasMoreElements()) {
        headers.add(headerName, headerValues.nextElement());
      }
    }

    return headers;
  }

  @Override
  public final String getMethod() {
    return request.getMethod();
  }

  @Override
  public final String getPathInfo() {
    return request.getPathInfo();
  }

  @Override
  public final String getPathTranslated() {
    return request.getPathTranslated();
  }

  @Override
  public final MultiValueMap<String, String> getParameters() {
    MultiValueMap<String, String> parameters = new MultiValueMap<>();
    Enumeration<String> names = request.getParameterNames();
    while (names.hasMoreElements()) {
      String parameterName = names.nextElement();
      Enumeration<String> parameterValues = Collections.enumeration(Arrays.asList(request.getParameterValues(parameterName)));
      while (parameterValues.hasMoreElements()) {
        parameters.add(parameterName, parameterValues.nextElement());
      }
    }

    return parameters;
  }

  @Override
  public final String getQueryString() {
    return request.getQueryString();
  }

  @Override
  public final String getRemoteAddr() {
    return request.getRemoteAddr();
  }

  @Override
  public final int getRemotePort() {
    return request.getRemotePort();
  }

  @Override
  public final String getRequestURL() {
    return request.getRequestURL().toString();
  }

  @Override
  public final String getServerName() {
    return request.getServerName();
  }

  @Override
  public final int getServerPort() {
    return request.getServerPort();
  }

  @Override
  public final String getServerProtocol() {
    return request.getProtocol();
  }

  @Override
  public final String getSessionId() {
    return null;
  }

  @Override
  public final long getSessionCreateTime() {
    return sessionCreateTime;
  }

  public final void setSessionCreateTime(long sessionCreateTime) {
    this.sessionCreateTime = sessionCreateTime;
  }

  @Override
  public final String getSessionTraceId() {
    if (requestContext == null) {
      return null;
    }

    return requestContext.getSessionTraceId();
  }

  @Override
  public final String getTraceId() {
    return GlobalTracer.get().activeSpan().context().toTraceId();
  }

  @Override
  public final String getUserId() {
    if (session == null) {
      return null;
    }

    return session.getUserId();
  }
}
