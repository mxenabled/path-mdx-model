package com.mx.path.model.mdx.model.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoreFields extends MdxBase<CoreFields> {
  private Boolean hasApr;
  private Boolean hasApy;
  private Boolean hasAvailableBalance;
  private Boolean hasAvailableCredit;
  private Boolean hasBalance;
  private Boolean hasCashAdvanceApr;
  private Boolean hasCashSurrenderValue;
  private Boolean hasCreditLimit;
  private Boolean hasCurrencyCode;
  private Boolean hasDayPaymentIsDue;
  private Boolean hasDeathBenefit;
  private Boolean hasMonthlyTransferLimit;
  private Boolean hasHoldTotal;
  private Boolean hasInsuredName;
  private Boolean hasInterestPaidPreviousYear;
  private Boolean hasInterestPaidYtd;
  private Boolean hasInterestRate;
  private Boolean hasIsClosed;
  private Boolean hasIsHidden;
  private Boolean hasLastPayment;
  private Boolean hasLastPaymentDate;
  private Boolean hasLoanAmount;
  private Boolean hasMaturesDate;
  private Boolean hasMemberGuid;
  private Boolean hasMemberId;
  private Boolean hasMinimumBalance;
  private Boolean hasMinimumPayment;
  private Boolean hasMonthlyTransferCount;
  private Boolean hasName;
  private Boolean hasNickname;
  private Boolean hasOriginalBalance;
  private Boolean hasPastDueAmount;
  private Boolean hasPastDueDate;
  private Boolean hasPaymentDueDate;
  private Boolean hasPayoffBalance;
  private Boolean hasPayOutAmount;
  private Boolean hasPendingBalance;
  private Boolean hasPendingTransactionsTotal;
  private Boolean hasPremiumAmount;
  private Boolean hasRoutingNumber;
  private Boolean hasStartedDate;
  private Boolean hasStatementClosedOn;
  private Boolean hasStatementLateCharges;
  private Boolean hasSubtype;
  private Boolean hasType;
}
