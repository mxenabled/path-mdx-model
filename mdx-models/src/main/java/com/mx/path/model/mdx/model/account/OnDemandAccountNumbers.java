package com.mx.path.model.mdx.model.account;

import com.mx.path.model.mdx.model.MdxBase;

/**
 * Wraps account numbers for an account to help with OnDemand serialization.
 */
public final class OnDemandAccountNumbers extends MdxBase<OnDemandAccountNumbers> {
  private String id;
  private AccountNumbers accountNumbers;

  public OnDemandAccountNumbers(AccountNumbers accountNumbers, String id) {
    this.id = id;
    this.accountNumbers = accountNumbers;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AccountNumbers getAccountNumbers() {
    return accountNumbers;
  }

  public void setAccountNumbers(AccountNumbers accountNumbers) {
    this.accountNumbers = accountNumbers;
  }
}
