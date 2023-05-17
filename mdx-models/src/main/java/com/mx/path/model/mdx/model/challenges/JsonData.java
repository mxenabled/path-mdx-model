package com.mx.path.model.mdx.model.challenges;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.core.common.configuration.ConfigurationField;
import com.mx.path.model.mdx.model.MdxBase;

@Data
@EqualsAndHashCode(callSuper = true)
public final class JsonData extends MdxBase<JsonData> {
  @ConfigurationField
  private String jsonType;
}
