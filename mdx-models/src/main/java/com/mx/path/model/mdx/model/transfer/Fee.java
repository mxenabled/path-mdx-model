package com.mx.path.model.mdx.model.transfer;

import lombok.Data;

import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class Fee extends MdxBase<Fee> {

  private Double amount;
  private String description;
}
