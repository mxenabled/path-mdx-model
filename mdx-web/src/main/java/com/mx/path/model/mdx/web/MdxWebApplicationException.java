package com.mx.path.model.mdx.web;

import com.mx.path.core.common.exception.PathSystemException;

public class MdxWebApplicationException extends PathSystemException {
  public MdxWebApplicationException(String message) {
    super(message);
  }

  public MdxWebApplicationException(String message, Throwable cause) {
    super(message, cause);
  }
}
