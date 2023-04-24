package com.mx.path.model.mdx.model.cross_account_transfer;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.mx.common.models.MdxBase;
import com.mx.path.model.mdx.model.MdxRelationId;
import com.mx.path.model.mdx.model.UserIdProvider;
import com.mx.path.model.mdx.model.account.Account;

@EqualsAndHashCode(callSuper = true)
@Data
public class CrossAccountRecurringTransfer extends MdxBase<CrossAccountRecurringTransfer> {
  private String accountTypeId;
  private Integer accountTypeNumber;
  private Double amount;
  private String confirmationId;
  private String destinationId;
  private Integer endAfterAmount;
  private Integer endAfterCount;
  private LocalDate endOn;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String fromAccountId;
  private String frequencyId;
  private String id;
  private LocalDate lastTransferOn;
  private String memo;
  private LocalDate nextTransferOn;
  private String status;
  private LocalDate startOn;

  public CrossAccountRecurringTransfer() {
    UserIdProvider.setUserId(this);
  }
}
