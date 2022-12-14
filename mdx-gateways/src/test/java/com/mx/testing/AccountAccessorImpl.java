package com.mx.testing;

import lombok.Setter;

import com.mx.accessors.account.AccountBaseAccessor;
import com.mx.accessors.account.TransactionBaseAccessor;
import com.mx.common.accessors.AccessorConfiguration;

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
