package com.mx.path.model.mdx.model.challenges;

import lombok.Data;

import com.mx.path.core.common.configuration.ConfigurationField;
import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class ShowStatus extends MdxBase<ShowStatus> {
  @ConfigurationField
  private String message;

  @ConfigurationField
  private String type;
}
