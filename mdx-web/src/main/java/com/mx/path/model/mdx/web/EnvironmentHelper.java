package com.mx.path.model.mdx.web;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@SuppressFBWarnings
@Component
public class EnvironmentHelper {
  private static Environment environment;

  public static final Environment getEnvironment() {
    return environment;
  }

  @Autowired
  public final void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
