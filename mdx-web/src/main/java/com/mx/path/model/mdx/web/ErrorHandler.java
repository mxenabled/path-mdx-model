package com.mx.path.model.mdx.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import com.google.gson.JsonObject;
import com.mx.common.accessors.PathResponseStatus;
import com.mx.common.exception.ExceptionReporter;
import com.mx.common.exception.PathRequestException;
import com.mx.common.exception.PathRequestExceptionWrapper;
import com.mx.path.model.context.RequestContext;
import com.mx.path.model.context.Session;
import com.mx.path.model.context.facility.Facilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

  @Autowired
  private Environment environment;

  @Autowired
  private ExceptionReporter springExceptionReporter;

  // Private

  private JsonObject exceptionToJson(PathRequestException ex) {
    JsonObject attributes = new JsonObject();

    attributes.addProperty("message", ex.getMessage());
    if (ex.getUserMessage() != null) {
      attributes.addProperty("user_message", ex.getUserMessage());
    }
    if (ex.getErrorTitle() != null) {
      attributes.addProperty("error_title", ex.getErrorTitle());
    }
    if (ex.getCode() != null) {
      attributes.addProperty("error_code", ex.getCode());
    }

    JsonObject body = new JsonObject();
    body.add("error", attributes);

    return body;
  }

  @SuppressWarnings("PMD.CyclomaticComplexity")
  @SuppressFBWarnings("BC_UNCONFIRMED_CAST")
  public final void handleException(Exception ex, HttpServletResponse response, HttpServletRequest request) throws IOException {
    JsonObject restResponse;
    PathResponseStatus status = PathResponseStatus.INTERNAL_ERROR;
    Map<String, String> headers = null;

    if (PathRequestExceptionWrapper.class.isAssignableFrom(ex.getClass())) {
      PathRequestException pathRequestException = (PathRequestException) ex;
      restResponse = exceptionToJson(
          ExceptionFormatter.ensureUserMessage(pathRequestException));
      headers = pathRequestException.getHeaders();
      status = pathRequestException.getStatus();
      if (status == PathResponseStatus.INTERNAL_ERROR || pathRequestException.shouldReport()) {
        reportException(ex.getCause(), request);
      }
    } else if (ex.getCause() != null && PathRequestException.class.isAssignableFrom(ex.getCause().getClass())) {
      PathRequestException pathRequestException = (PathRequestException) ex.getCause();
      restResponse = exceptionToJson(
          ExceptionFormatter.ensureUserMessage(pathRequestException));
      headers = pathRequestException.getHeaders();
      status = pathRequestException.getStatus();
      if (status == PathResponseStatus.INTERNAL_ERROR || pathRequestException.shouldReport()) {
        reportException(pathRequestException, request);
      }
    } else if (PathRequestException.class.isAssignableFrom(ex.getClass())) {
      PathRequestException pathRequestException = (PathRequestException) ex;
      restResponse = exceptionToJson(
          ExceptionFormatter.ensureUserMessage(pathRequestException));
      headers = pathRequestException.getHeaders();
      status = pathRequestException.getStatus();
      if (status == PathResponseStatus.INTERNAL_ERROR || pathRequestException.shouldReport()) {
        reportException(pathRequestException, request);
      }
    } else {
      //Logging only if it is not an MDX or hystrix exception.
      LOGGER.error("Uncaught exception", ex);
      response.getStatus();
      reportException(ex, request);
      restResponse = exceptionToJson(
          ExceptionFormatter.convertToPathRequestException(ex, response));
    }

    // Temporarily put back error response writer.
    // This fails when a filter fails.
    //    HttpServletResponse resp = response;
    response.reset();
    response.setStatus(status.value());
    response.setContentType("application/vnd.mx.mdx.v6+json");

    if (headers != null) {
      headers.forEach(response::setHeader);
    }

    response.getOutputStream().write(restResponse.toString().getBytes(StandardCharsets.UTF_8));
  }

  private void reportException(Throwable ex, HttpServletRequest request) {
    WebExceptionContext context = new WebExceptionContext(request, environment, Session.current(), RequestContext.current());
    if (Session.current() != null) {
      context.setSessionCreateTime(Session.current().getStartedAt().toEpochSecond(ZoneOffset.UTC));
      context.getContext().put("client_id", RequestContext.current().getClientId());
    }
    exceptionReporter().report(ex, ex.getMessage(), context);
  }

  private ExceptionReporter exceptionReporter() {
    if (Facilities.getExceptionReporter(RequestContext.current().getClientId()) != null) {
      return Facilities.getExceptionReporter(RequestContext.current().getClientId());
    }

    return springExceptionReporter;
  }
}
