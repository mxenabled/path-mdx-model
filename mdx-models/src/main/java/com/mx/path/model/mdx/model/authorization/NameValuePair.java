package com.mx.path.model.mdx.model.authorization;

import com.mx.path.model.mdx.model.MdxBase;

public class NameValuePair extends MdxBase<NameValuePair> {
  private String name;
  private String value;

  public final String getName() {
    return name;
  }

  public final void setName(String newName) {
    this.name = newName;
  }

  public final String getValue() {
    return value;
  }

  public final void setValue(String newValue) {
    this.value = newValue;
  }
}
