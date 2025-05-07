package com.mx.path.model.mdx.model.transfer;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxRelationId;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.challenges.Challenge;

@EqualsAndHashCode(callSuper = true)
@Data
public final class Transfer extends MdxBase<Transfer> {

  private List<Challenge> challenges;

  private Double amount;
  private String amountOptionId;
  private String confirmationId;
  private Long createdAt;
  private LocalDate createdOn;
  @Deprecated
  private String flow;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String fromAccountId;
  private String repaymentAccountId;
  private String id;
  private String memo;
  private Long postedAt;
  private LocalDate postOn;
  private LocalDate postedOn;
  private String recurringTransferId;
  private String status;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String toAccountId;
  private String transferType;
  private Long updatedAt;
  private LocalDate updatedOn;

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }
}
