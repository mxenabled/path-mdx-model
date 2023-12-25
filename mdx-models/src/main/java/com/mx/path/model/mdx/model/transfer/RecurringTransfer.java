package com.mx.path.model.mdx.model.transfer;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxRelationId;
import com.mx.path.model.mdx.model.UserIdProvider;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.challenges.Challenge;

@EqualsAndHashCode(callSuper = true)
@Data
public final class RecurringTransfer extends MdxBase<RecurringTransfer> {
  private List<Challenge> challenges;

  private Double amount;
  private String confirmationId;
  private Double endAfterAmount;
  private Integer endAfterCount;
  private LocalDate endOn;
  @Deprecated
  private String flow;
  private String frequencyId;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String fromAccountId;
  private String id;
  private LocalDate lastTransferOn;
  private String memo;
  private LocalDate nextTransferOn;
  private LocalDate startOn;
  private String status;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String toAccountId;
  private String transferType;

  public RecurringTransfer() {
    UserIdProvider.setUserId(this);
  }

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }
}
