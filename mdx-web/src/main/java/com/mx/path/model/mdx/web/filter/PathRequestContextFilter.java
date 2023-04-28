package com.mx.path.model.mdx.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.context.RequestContext;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Request context filter
 * <p>
 * This filter inserts context headers provided by caller for logging purposes.
 * These values are inserted into the MDC.
 */
@Component
@Order(FilterOrderSequence.REQUEST_CONTEXT_FILTER)
public class PathRequestContextFilter extends OncePerRequestFilter {

  // Statics
  public static final String CLIENT_GUID_HEADER = "mx-client-guid";
  public static final String FEATURE_HEADER = "mx-feature";
  public static final String USER_GUID_HEADER = "mx-user-guid";
  public static final String DEVICE_IP_ADDRESS = "mx-device-ip-address";
  public static final String JOB_TYPE_HEADER = "mdx-job-type";
  public static final String SESSION_TRACE_ID_HEADER = "mx-session-trace-id";
  public static final String DEVICE_TRACE_ID_HEADER = "mx-device-trace-id";
  public static final String REFRESH_TOKEN_HEADER = "mx-refresh-token";

  // Protected
  @Override
  protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String clientGuid = request.getHeader(CLIENT_GUID_HEADER);
    String feature = request.getHeader(FEATURE_HEADER);
    String userGuid = request.getHeader(USER_GUID_HEADER);
    String jobType = request.getHeader(JOB_TYPE_HEADER);
    String userId = null;
    String clientId = null;
    String originatingIp = request.getHeader(DEVICE_IP_ADDRESS);
    String sessionTraceId = request.getHeader(SESSION_TRACE_ID_HEADER);
    String deviceTraceId = request.getHeader(DEVICE_TRACE_ID_HEADER);
    String refreshToken = request.getHeader(REFRESH_TOKEN_HEADER);

    String path = request.getRequestURI();
    if (path != null) {
      String[] urlParts = path.split("/");
      clientId = urlParts[1];
      for (int i = 2; i < urlParts.length; i++) {
        if (urlParts[i].equalsIgnoreCase("users") && i + 1 < urlParts.length) {
          userId = urlParts[i + 1];
          break;
        }
      }
    }

    RequestContext.RequestContextBuilder builder = RequestContext.builder()
        .clientGuid(clientGuid)
        .clientId(clientId)
        .deviceTraceId(deviceTraceId)
        .feature(feature)
        .path(path)
        .sessionTraceId(sessionTraceId)
        .userGuid(userGuid)
        .userId(userId)
        .originatingIP(originatingIp);

    if (Strings.isNotBlank(jobType)) {
      builder.header("mdx-job-type", jobType);
    }

    if (Strings.isNotBlank(refreshToken)) {
      builder.header(REFRESH_TOKEN_HEADER, refreshToken);
    }

    builder.build().register();

    try {
      filterChain.doFilter(request, response);
    } finally {
      RequestContext.clear();
    }
  }
}
