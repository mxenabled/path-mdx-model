package com.mx.path.model.mdx.web;

import com.mx.path.core.context.tracing.CustomTracer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.opentracing.Tracer;

/**
 * Spring component that sets the tracing context in CustomTracer.
 */
@Component
public class TracingProviderComponent {
  @Autowired
  public final void provideTracer(Tracer tracer) {
    CustomTracer.setTracer(tracer);
  }
}
