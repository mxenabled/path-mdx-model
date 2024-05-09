package com.mx.path.model.mdx.model.account.alerts;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlertCriteria extends MdxBase<AlertCriteria> {
  private String id;
  private Integer intValue;
  private String name;
  private Double numberValue;
  private String stringValue;
  private String type;
}
