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
 * A series that creates {@link ExternalTransfer}s on a repeating cycle.
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/external_transfer/#recurring-external-transfers">Recurring external transfers</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class RecurringExternalTransfer extends MdxBase<RecurringExternalTransfer> {
  private String id;
  private BigDecimal amount;
  private MdxList<Challenge> challenges;
  private String confirmationId;
  private Long createdAt;
  private Integer endAfterCount;
  private LocalDate endOn;
  private String frequencyId;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String fromAccountId;
  private String fromExternalAccountId;
  private LocalDate lastTransferOn;
  private String memo;
  private LocalDate nextTransferOn;
  private LocalDate startOn;
  private String status;
  @Getter(onMethod_ = { @MdxRelationId(referredClass = Account.class) })
  private String toAccountId;
  private String toExternalAccountId;
}
