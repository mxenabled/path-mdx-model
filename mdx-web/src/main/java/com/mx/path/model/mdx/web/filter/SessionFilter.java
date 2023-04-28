package com.mx.path.model.mdx.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.context.Session;
import com.mx.path.core.utility.jwt.JWTUtility;
import com.mx.path.model.mdx.web.PathTools;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Session Filter
 * <p>
 * This filter initializes a current Session for the request by reading the
 * session from session persistence based on the mx-session-key header.
 * <p>
 * The session can then be accessed via {@code Session.current()}
 */
@Component
@Order(FilterOrderSequence.SESSION_FILTER)
public class SessionFilter extends OncePerRequestFilter {

  /**
   * MDX Session Header Name
   */
  public static final String SESSION_HEADER = "mx-session-key";
  public static final String SESSION_ONDEMAND_HEADER = "mdx-session-key";
  public static final String JWT_HEADER = "mx-auth-token";

  @Override
  protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String sessionIdFromJWT = getSessionKeyFromJWT(request);
    String sessionId = !sessionIdFromJWT.isEmpty() ? sessionIdFromJWT : request.getHeader(SESSION_HEADER);
    if (Strings.isBlank(sessionId)) {
      sessionId = request.getHeader(SESSION_ONDEMAND_HEADER);
    }

    String path = request.getServletPath();

    if (Strings.isBlank(sessionId)) {
      sessionId = PathTools.extractSessionId(path);
    }

    if (sessionId != null) {
      Session.loadSession(sessionId);

      // If this is a login request and there is a session provided, delete it.
      // This will ensure a clean slate for the request and remove the rogue session
      if (PathTools.isAuthenticationPath(path)) {
        Session.deleteCurrent();
      }
    }

    try {
      filterChain.doFilter(request, response);
      // Load singleton again here prior to save in the case that a new session is created
      //   (e.g. during login)
      if (Session.current() != null) {
        Session.current().save();
      }
    } finally {
      Session.clearSession();
    }
  }

  private static String getSessionKeyFromJWT(HttpServletRequest request) {
    String currentJWT = request.getHeader(JWT_HEADER);
    return Strings.isNotBlank(currentJWT)
        ? JWTUtility.getClaimFromIdToken(currentJWT, "sessionId", String.class, "")
        : "";
  }
}
