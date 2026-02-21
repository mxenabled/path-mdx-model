package com.mx.path.model.mdx.web;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class ExceptionResolverWebMvcConfigurer implements WebMvcConfigurer {

  @Override
  public final void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    resolvers.removeIf(resolver -> resolver.getClass().equals(DefaultHandlerExceptionResolver.class));
    resolvers.add(new DefaultHandlerExceptionResolver() {

      @Override
      @Nullable
      @SuppressWarnings("NullableProblems")
      protected ModelAndView handleDisconnectedClientException(
          Exception ex, HttpServletRequest request, HttpServletResponse response, @Nullable Object handler) {

        // Return null instead of an empty ModelAndView, so that the disconnected client exception is not suppressed
        return null;
      }
    });
  }
}
