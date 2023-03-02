package com.mx.path.model.mdx.web.filter.hmac;

import com.mx.common.exception.PathSystemException;

public class PathHMACException extends PathSystemException {
  public PathHMACException(String message) {
    super(message);
  }

  public PathHMACException(String message, Throwable cause) {
    super(message, cause);
  }
}
