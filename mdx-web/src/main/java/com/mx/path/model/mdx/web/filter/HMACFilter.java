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

import com.mx.common.accessors.ResourceNotFoundException;
import com.mx.path.model.mdx.web.PathTools;
import com.mx.path.model.mdx.web.filter.hmac.HMACConfiguration;
import com.mx.path.model.mdx.web.filter.hmac.HMACConfigurationFactory;
import com.mx.path.model.mdx.web.filter.hmac.MDXHmac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(FilterOrderSequence.HMAC_FILTER)
public class HMACFilter extends OncePerRequestFilter {

  /**
  * Path patterns that do not get an Architect Client
  */
  private static final List<Predicate<String>> SKIP_PATHS = new ArrayList<Predicate<String>>();

  /**
   * Initialize the skip path predicates
   */
  static {
    SKIP_PATHS.add(Pattern.compile("^\\/health$").asPredicate());
    SKIP_PATHS.add(Pattern.compile("^\\/actuator/loggers\\/[A-Z\\/\\.]*$").asPredicate());
    SKIP_PATHS.add(Pattern.compile("^\\/actuator$").asPredicate());
    SKIP_PATHS.add(Pattern.compile("^\\/actuator\\/[a-z\\/\\.]*$").asPredicate());
  }

  @Autowired(required = false)
  private HMACConfigurationFactory hmacConfigurationFactory;

  private boolean isSkipPath(String path) {
    return SKIP_PATHS.stream().anyMatch(p -> p.test(path));
  }

  public final void setHmacConfigurationFactory(HMACConfigurationFactory hmacConfigurationFactory) {
    this.hmacConfigurationFactory = hmacConfigurationFactory;
  }

  @Override
  protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest(request);
    String path = request.getServletPath();

    if (!isSkipPath(path)) {
      String clientId = PathTools.getClientIdFromPath(request.getRequestURI());

      if (hmacConfigurationFactory != null) {
        HMACConfiguration configuration = hmacConfigurationFactory.get(clientId);
        if (configuration == null) {
          throw new ResourceNotFoundException(
              "Client configuration for " + clientId + " not found.",
              "Client configuration for " + clientId + " not found.");
        } else {
          String hmacSalt = configuration.getHmacSalt();
          if (hmacSalt != null) {
            MDXHmac.validateRequest(multiReadRequest, hmacSalt);
          }
        }
      }
    }

    filterChain.doFilter(multiReadRequest, response);
  }
}
