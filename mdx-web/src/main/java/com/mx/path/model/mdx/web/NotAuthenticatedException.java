package com.mx.path.model.mdx.web;

import com.mx.path.core.common.accessor.UnauthorizedException;

public class NotAuthenticatedException extends UnauthorizedException {
  public NotAuthenticatedException() {
    super("Not Authenticated", "Not Authenticated");
  }

  public NotAuthenticatedException(String message, String userMessage) {
    super(message, userMessage);
  }

  public NotAuthenticatedException(String message, String userMessage, Throwable cause) {
    super(message, userMessage, cause);
  }
}
