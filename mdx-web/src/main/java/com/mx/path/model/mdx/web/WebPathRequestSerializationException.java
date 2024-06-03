package com.mx.path.model.mdx.web;

import com.mx.path.core.common.accessor.PathResponseStatus;
import com.mx.path.core.common.serialization.PathRequestSerializableException;

public class WebPathRequestSerializationException extends PathRequestSerializableException {
  public WebPathRequestSerializationException(String originalType, Throwable cause) {
    super();
    super.initCause(cause);
    this.setOriginalType(originalType);
    this.setStatus(PathResponseStatus.BAD_REQUEST);
  }
}
