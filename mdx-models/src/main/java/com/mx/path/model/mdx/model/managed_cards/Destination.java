package com.mx.path.model.mdx.model.managed_cards;

import com.mx.path.model.mdx.model.MdxBase;

public final class Destination extends MdxBase<Destination> {

  private String id;

  public Destination() {
  }

  public String getId() {
    return id;
  }

  public void setId(String newId) {
    this.id = newId;
  }
}
