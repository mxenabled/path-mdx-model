package com.mx.path.model.mdx.model.challenges;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
@Data
@EqualsAndHashCode(callSuper = true)
public final class JsonData extends MdxBase<JsonData> {
  private String jsonType;
}
