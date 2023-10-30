package com.mx.path.service.splunk;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gets Logger based on clientId
 */
public class SplunkLoggerFactory {

  private static final Map<String, Logger> LOGGERS = new HashMap<>();

  public static Logger getLogger(String clientId) {
    String splunkLogger = "splunk." + clientId;
    if (!LOGGERS.containsKey(splunkLogger)) {
      synchronized (LOGGERS) {
        if (!LOGGERS.containsKey(splunkLogger)) {
          Logger foundLogger = LoggerFactory.getLogger(splunkLogger);
          LOGGERS.put(splunkLogger, foundLogger);
        }
      }
    }
    return LOGGERS.get(splunkLogger);
  }
}
