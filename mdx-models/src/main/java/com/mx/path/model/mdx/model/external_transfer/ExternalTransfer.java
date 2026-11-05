package com.mx.path.model.mdx.model.external_transfer;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.MdxRelationId;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.challenges.Challenge;

/**
 * A single movement of money between a held account and an external account, or between two external accounts.
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/external_transfer/#external-transfers">External transfers</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ExternalTransfer extends MdxBase<ExternalTransfer> {
  private String id;
  private BigDecimal amount;
  private MdxList<Challenge> challenges;
  private String confirmationId;
  private Long createdAt;
  private LocalDate deliverOn;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String fromAccountId;
  private String fromExternalAccountId;
  private String memo;
  private LocalDate postOn;
  private LocalDate postedOn;
  private String recurringExternalTransferId;
  private String status;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String toAccountId;
  private String toExternalAccountId;
  private Long updatedAt;
}
