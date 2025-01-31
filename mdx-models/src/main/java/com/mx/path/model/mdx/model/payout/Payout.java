package com.mx.path.model.mdx.model.payout;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.challenges.Challenge;

@Data
@EqualsAndHashCode(callSuper = true)
public final class Payout extends MdxBase<Payout> {
  private String accountId;
  private Double amount;
  private MdxList<Challenge> challenges;
  private String confirmationId;
  private LocalDate expiresOn;
  private Double fee;
  private String id;
  private String memo;
  private String nameCheckWarningTitle;
  private String nameCheckWarningText;
  private String payoutMethodId;
  private String payoutRequestId;
  private String recipientId;
  private String recurringPayoutId;
  private String status;
  private String senderName;
  private LocalDate sendOn;
  private Long sentAt;
  private LocalDate sentOn;
  private String speedText;
  private String token;
  private String type;
  private String challengeQuestion;
  private String challengeAnswer;
}
