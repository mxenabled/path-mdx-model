package com.mx.path.model.mdx.model.account;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public class StopPayment extends MdxBase<StopPayment> {
  private Double amount;
  private LocalDate effectiveOn;
  private String endingCheckNumber;
  private Double fee;
  private String id;
  private String payee;
  private String startingCheckNumber;
  private String stopPaymentReasonId;
  private String type;
}
