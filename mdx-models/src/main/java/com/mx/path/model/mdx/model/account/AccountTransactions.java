package com.mx.path.model.mdx.model.account;

import com.mx.path.model.mdx.model.MdxBase;

/**
 * Represents an account with a page of transactions, MDX OnDemand style.
 */
public class AccountTransactions extends MdxBase<AccountTransactions> {
  private String id;
  private TransactionsPage transactions;

  public final String getId() {
    return id;
  }

  public final void setId(String id) {
    this.id = id;
  }

  public final TransactionsPage getTransactions() {
    return transactions;
  }

  public final void setTransactions(TransactionsPage transactions) {
    this.transactions = transactions;
  }
}
