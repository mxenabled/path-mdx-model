package com.mx.path.model.mdx.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mx.path.core.common.accessor.UnauthorizedException;
import com.mx.path.core.context.Session;
import com.mx.path.core.context.Session.SessionState;
import com.mx.path.model.mdx.web.PathTools;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Require Authentication Filter
 *
 * This filter checks for a current session on all routes under
 * :clientId/users/* For this to function properly, the SessionFilter must have
 * a higher precedence that this.
 */
@Component
@Order(FilterOrderSequence.AUTH_FILTER)
public class RequireAuthenticationFilter extends OncePerRequestFilter {

  @Override
  @SuppressWarnings("PMD.CyclomaticComplexity")
  protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String path = request.getServletPath();

    // Check session required
    if (PathTools.isSessionRequiredPath(path)
        && Session.current() == null) {

      throw new UnauthorizedException("Session not found", "Session not found");
    }

    if (PathTools.isAuthenticationRequiredPath(path)
        && (Session.current() == null
            || Session.current().getSessionState() != SessionState.AUTHENTICATED)) {

      throw new UnauthorizedException("Not Authenticated", "Not Authenticated");
    }

    if (PathTools.isUserRequiredPath(path)) {
      String userId = PathTools.extractUserId(path);
      if (userId != null && Session.current().getUserId() != null && !userId.equals(Session.current().getUserId())) {
        throw new UnauthorizedException("User does not match user session", "Not Authenticated");
      }
    }
    filterChain.doFilter(request, response);
  }
}
