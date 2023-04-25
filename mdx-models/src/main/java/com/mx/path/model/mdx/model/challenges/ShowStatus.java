package com.mx.path.model.mdx.model.challenges;

import com.mx.path.model.mdx.model.MdxBase;

public final class ShowStatus extends MdxBase<ShowStatus> {
  private String message;
  private String type;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
