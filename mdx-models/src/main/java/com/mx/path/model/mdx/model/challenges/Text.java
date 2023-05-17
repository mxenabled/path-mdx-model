package com.mx.path.model.mdx.model.challenges;

import lombok.Data;

import com.mx.path.core.common.configuration.ConfigurationField;
import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class Text extends MdxBase<Text> {
  @ConfigurationField
  private String characterType;
}
