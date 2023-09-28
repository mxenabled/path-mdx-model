package com.mx.path.model.mdx.model.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class StopPaymentReason extends MdxBase<StopPaymentReason> {
  private Double fee;
  private String id;
  private String name;
}
