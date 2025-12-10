package com.mx.path.model.mdx.model.p2p_transfer;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountData extends MdxBase<AccountData> {
  private String accountId;
  private BigDecimal availableBalance;
  private BigDecimal balance;
}
