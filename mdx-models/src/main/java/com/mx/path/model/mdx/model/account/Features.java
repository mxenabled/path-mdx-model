package com.mx.path.model.mdx.model.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class Features extends MdxBase<Features> {
  private Boolean hasCardManager;
  private Boolean hasCheckReorder;
  private Boolean hasDirectDepositManagement;
  private Boolean hasEditAddress;
  private Boolean hasEditNickname;
  private Boolean hasFeature;
  private Boolean hasOverdraft;
  private Boolean hasOverdraftEnrollment;
  private Boolean hasStatements;
  private Boolean hasStatementsEnrollment;
  private Boolean hasStopPayment;
}
