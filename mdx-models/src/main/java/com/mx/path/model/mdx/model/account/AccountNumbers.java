package com.mx.path.model.mdx.model.account;

import com.mx.path.model.mdx.model.MdxBase;

public final class AccountNumbers extends MdxBase<AccountNumbers> {
  private String id;
  private AccountNumber accountNumbers;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AccountNumber getAccountNumbers() {
    return accountNumbers;
  }

  public void setAccountNumbers(AccountNumber accountNumbers) {
    this.accountNumbers = accountNumbers;
  }
}
