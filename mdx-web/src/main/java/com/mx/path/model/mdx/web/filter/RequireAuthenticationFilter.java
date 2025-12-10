package com.mx.path.model.mdx.web.filter;

import java.io.IOException;

import com.mx.path.core.common.accessor.UnauthorizedException;
import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.context.Session;
import com.mx.path.core.context.Session.SessionState;
import com.mx.path.model.mdx.web.NotAuthenticatedException;
import com.mx.path.model.mdx.web.PathTools;
import com.mx.path.model.mdx.web.SessionNotFoundException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

      throw new SessionNotFoundException();
    }

    if (PathTools.isAuthenticationRequiredPath(path)
        && (Session.current() == null
            || Session.current().getSessionState() != SessionState.AUTHENTICATED)) {

      throw new NotAuthenticatedException();
    }

    if (PathTools.isUserRequiredPath(path)) {
      if (Strings.isBlank(Session.current().getUserId())) {
        throw new UnauthorizedException("UserId missing in authenticated session", "Not Authenticated");
      }
      String userId = PathTools.extractUserId(path);
      if (userId != null && !userId.equals(Session.current().getUserId())) {
        throw new UnauthorizedException("User does not match user session", "Not Authenticated");
      }
    }
    filterChain.doFilter(request, response);
  }
}
