package com.mx.path.model.mdx.model.cross_account_transfer;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxRelationId;
import com.mx.path.model.mdx.model.UserIdProvider;
import com.mx.path.model.mdx.model.account.Account;

@EqualsAndHashCode(callSuper = true)
@Data
public final class CrossAccountTransfer extends MdxBase<CrossAccountTransfer> {
  @Deprecated
  private String accountHolder;
  @Deprecated
  private String accountType;
  private String accountTypeId;
  private Integer accountTypeNumber;
  private Double amount;
  private String confirmationId;
  private String destinationId;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String fromAccountId;
  private String id;
  private String memo;
  private LocalDate postOn;
  private LocalDate postedOn;
  private String recurringCrossAccountTransferId;
  private String status;
  @Deprecated
  private String toAccountNumber;

  public CrossAccountTransfer() {
    UserIdProvider.setUserId(this);
  }
}
