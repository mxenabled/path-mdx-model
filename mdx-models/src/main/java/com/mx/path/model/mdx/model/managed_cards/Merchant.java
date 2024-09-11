package com.mx.path.model.mdx.model.managed_cards;

import lombok.Data;

import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class Merchant extends MdxBase<Merchant> {
  private String id;
  private String logoUrl;
  private String name;
  private Status status;

  public enum Status {
    ALLOWED, BLOCKED
  }
}
