package com.mx.testing;

import lombok.Setter;

import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.model.mdx.accessor.account.AccountBaseAccessor;
import com.mx.path.model.mdx.accessor.account.TransactionBaseAccessor;

public class AccountAccessorImpl extends AccountBaseAccessor {
  @Setter
  TransactionBaseAccessor transactions;

  public AccountAccessorImpl(AccessorConfiguration configuration) {
    super(configuration);
    transactions = new TransactionAccessorImpl(configuration);
  }

  @Override
  public TransactionBaseAccessor transactions() {
    return transactions;
  }
}
