package com.mx.path.model.mdx.model.account;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxList;

/**
 * Wraps a list of accounts to help with OnDemand serialization.
 */
public class OnDemandAccounts extends MdxBase<OnDemandAccounts> {
  private MdxList<Account> accounts;

  public OnDemandAccounts() {
  }

  public OnDemandAccounts(MdxList<Account> accounts) {
    this.accounts = accounts;
  }

  public final void setAccounts(MdxList<Account> accounts) {
    this.accounts = accounts;
  }

  public final MdxList<Account> getAccounts() {
    return accounts;
  }
}
