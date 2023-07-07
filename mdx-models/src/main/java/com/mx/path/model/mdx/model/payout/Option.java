package com.mx.path.model.mdx.model.payout;

import com.mx.path.model.mdx.model.MdxBase;

public final class Option extends MdxBase<Option> {
  private String id;
  private String name;

  public Option() {
  }

  public String getId() {
    return id;
  }

  public void setId(String newId) {
    this.id = newId;
  }

  public String getName() {
    return name;
  }

  public void setName(String newName) {
    this.name = newName;
  }
}
