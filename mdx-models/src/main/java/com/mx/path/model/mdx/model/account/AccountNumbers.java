package com.mx.path.model.mdx.model.account;

import com.mx.path.model.mdx.model.MdxBase;

public final class AccountNumbers extends MdxBase<AccountNumbers> {
  private String accountNumber;
  private String routingNumber;

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getRoutingNumber() {
    return routingNumber;
  }

  public void setRoutingNumber(String routingNumber) {
    this.routingNumber = routingNumber;
  }
}
