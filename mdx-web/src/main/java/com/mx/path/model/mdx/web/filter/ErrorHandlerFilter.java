package com.mx.path.model.mdx.web.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Error handler filter
 */
@Component
@Order(FilterOrderSequence.ERROR_FILTER)
public class ErrorHandlerFilter extends OncePerRequestFilter {

  @Autowired
  private ErrorHandler errorHandler;

  public final void setErrorHandler(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  @Override
  protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (Exception ex) {
      errorHandler.handleException(ex, response, request);
    }
  }
}
