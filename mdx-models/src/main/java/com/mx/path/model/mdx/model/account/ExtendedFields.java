package com.mx.path.model.mdx.model.account;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExtendedFields extends MdxBase<ExtendedFields> {
  @SerializedName("extended_fields")
  private List<ExtendedFields> extendedFields;
  private Integer intValue;
  private String name;
  private Double numberValue;
  private String stringValue;
  private String type;
}
