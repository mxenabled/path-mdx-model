package com.mx.path.model.mdx.model.p2p_transfer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@Data
@EqualsAndHashCode(callSuper = true)
public class Source extends MdxBase<Source> {
  private String id;
  private SourceType type;
  private AccountData accountData;
  private DebitCardData debitCardData;

  public enum SourceType {
    ACCOUNT, DEBIT_CARD
  }
}
