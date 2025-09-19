package com.mx.path.model.mdx.model.challenges;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@Data
@MdxNested
@EqualsAndHashCode(callSuper = true)
public class Code extends MdxBase<Code> {
  private String keyboardType;
  private Integer numRetries;
  private Boolean resendButtonClicked;
  private String resendButtonState;
  private String resendButtonText;
}
