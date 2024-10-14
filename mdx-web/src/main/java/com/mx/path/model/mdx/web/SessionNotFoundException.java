package com.mx.path.model.mdx.web;

import com.mx.path.core.common.accessor.UnauthorizedException;

public class SessionNotFoundException extends UnauthorizedException {
  public SessionNotFoundException() {
    super("Session not found", "Session not found");
  }

  public SessionNotFoundException(String message, String userMessage) {
    super(message, userMessage);
  }

  public SessionNotFoundException(String message, String userMessage, Throwable cause) {
    super(message, userMessage, cause);
  }
}
