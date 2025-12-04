package com.mx.path.model.mdx.model.p2p_transfer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@Data
@EqualsAndHashCode(callSuper = true)
public class Recipient extends MdxBase<Recipient> {
  private String id;
  private String emailAddress;
  private String firstName;
  private String lastName;
  private String phoneNumber;
}
