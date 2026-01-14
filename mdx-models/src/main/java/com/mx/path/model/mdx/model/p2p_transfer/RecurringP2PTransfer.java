package com.mx.path.model.mdx.model.p2p_transfer;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.challenges.Challenge;

@Data
@EqualsAndHashCode(callSuper = true)
public class RecurringP2PTransfer extends MdxBase<RecurringP2PTransfer> {
  private String id;
  private BigDecimal amount;
  private MdxList<Challenge> challenges;
  private String confirmationId;
  private String deliveryMethod;
  private String durationType;
  private String durationValue;
  private String frequencyId;
  private String memo;
  private String recipientId;
  private String recipientVerificationAnswer;
  private String recipientVerificationQuestion;
  private LocalDate startOn;
  private String sourceId;
  private String status;
}
