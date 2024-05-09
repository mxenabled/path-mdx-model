package com.mx.path.model.mdx.model.account.alerts;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class DeliveryMethod extends MdxBase<DeliveryMethod> {
  private String channel;
  private String description;
  private String id;
  private String target;
}
