package com.mx.path.model.mdx.model.account;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;

public final class AccountNumber extends MdxBase<AccountNumber> {
  @SerializedName("account_number")
  private String accountNumber;
  @SerializedName("routing_number")
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
