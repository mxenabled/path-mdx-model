package com.mx.path.model.mdx.model.payment;

import com.mx.path.model.mdx.model.MdxBase;

public final class MerchantCategory extends MdxBase<MerchantCategory> {

  private String id;
  private String name;

  public MerchantCategory() {

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
