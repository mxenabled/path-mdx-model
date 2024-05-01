package com.mx.path.model.mdx.model.device;

import lombok.Data;

import com.mx.path.model.mdx.model.MdxBase;

@Data
public class VerificationMethod extends MdxBase<VerificationMethod> {
  public enum METHOD {
    CODE,
    LINK
  }

  private String id;
  private String phoneNumber;
  private String email;
  private METHOD method;
}
