package com.mx.path.model.mdx.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mx.path.model.mdx.web.PathTools;
import com.mx.path.model.mdx.web.SpringMdxGatewayManager;
import com.mx.path.model.mdx.web.controller.BaseController;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Sets gateway instance for controllers
 */
@Component
@Order(FilterOrderSequence.REPO_FILTER)
public class GatewayFilter extends OncePerRequestFilter {

  /**
   * Path patterns that do not get an Architect Client
   */
  private static final List<Predicate<String>> SKIP_PATHS = new ArrayList<>();

  /**
   * Initialize the skip path predicates
   */
  static {
    SKIP_PATHS.add(Pattern.compile("^\\/health$").asPredicate());
    SKIP_PATHS.add(Pattern.compile("^\\/actuator$").asPredicate());
    SKIP_PATHS.add(Pattern.compile("^\\/actuator\\/[a-z\\/\\.]*$").asPredicate());
  }

  // Private

  private boolean isSkipPath(String path) {
    return SKIP_PATHS.stream().anyMatch(p -> p.test(path));
  }

  @Override
  protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String path = request.getRequestURI();
    String clientId = PathTools.getClientIdFromPath(path);

    if (!isSkipPath(path)) {
      BaseController.setGateway(SpringMdxGatewayManager.getClientGateway(clientId));
    }

    try {
      filterChain.doFilter(request, response);
    } finally {
      BaseController.clearGateway();
    }
  }
}
