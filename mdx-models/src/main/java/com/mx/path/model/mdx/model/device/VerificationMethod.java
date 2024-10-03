package com.mx.path.model.mdx.model.device;

import lombok.Data;

import com.mx.path.model.mdx.model.MdxBase;

@Data
public class VerificationMethod extends MdxBase<VerificationMethod> {
  public enum TYPE {
    CODE,
    LINK
  }

  private String id;
  private String phoneNumber;
  private String emailAddress;
  private TYPE type;
}
