package com.mx.path.model.mdx.model.ach_transfer;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;

import com.mx.common.models.MdxBase;
import com.mx.path.model.mdx.model.MdxRelationId;
import com.mx.path.model.mdx.model.account.Account;

@Data
public final class AchTransfer extends MdxBase<AchTransfer> {
  private String id;
  private String achScheduledTransferId;
  private Double amount;
  private Long createdAt;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String fromAccountId;
  private String fromAchAccountId;
  private String memo;
  private LocalDate processedOn;
  private String status;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String toAccountId;
  private String toAchAccountId;
  private String transferType;
}
