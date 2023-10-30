package com.mx.path.service.splunk;

import com.mx.path.core.common.connect.Response;

import org.slf4j.Logger;

/**
 * Splunk logger dumps masked payload to the appropriate url via a token.
 * Url and token are defined in puppet
 * Logger is configured in logback-spring.xml
 */
public class SplunkLogger {

  private Logger logger;

  @SuppressWarnings("PMD.CyclomaticComplexity")
  public final void logInfo(Response response) {

    if (logger != null) {
      if (response.getException() != null) {
        logger.error(new SplunkLog(response).toString());
      } else {
        logger.info(new SplunkLog(response).toString());
      }
    }
  }

  public final void setLogger(Logger logger) {
    this.logger = logger;
  }
}
