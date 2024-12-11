package com.mx.path.model.mdx.model.authorization;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
@Data
@EqualsAndHashCode(callSuper = true)
public class NameValuePair extends MdxBase<NameValuePair> {
  private String name;
  private String value;
}
