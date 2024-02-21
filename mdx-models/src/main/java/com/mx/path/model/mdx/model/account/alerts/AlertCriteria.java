package com.mx.path.model.mdx.model.account.alerts;

import com.mx.path.model.mdx.model.MdxBase;

public class AlertCriteria extends MdxBase<AlertCriteria> {
  private String id;
  private String name;
  private String value;

  public final String getId() {
    return id;
  }

  public final void setId(String id) {
    this.id = id;
  }

  public final String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public final String getValue() {
    return value;
  }

  public final void setValue(String value) {
    this.value = value;
  }
}
