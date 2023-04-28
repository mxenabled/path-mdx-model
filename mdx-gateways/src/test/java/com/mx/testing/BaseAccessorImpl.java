package com.mx.testing;

import lombok.Setter;

import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.model.mdx.accessor.BaseAccessor;
import com.mx.path.model.mdx.accessor.account.AccountBaseAccessor;
import com.mx.path.model.mdx.accessor.id.IdBaseAccessor;

public class BaseAccessorImpl extends BaseAccessor {

  @Setter
  AccountBaseAccessor accounts;

  @Setter
  IdBaseAccessor id;

  public BaseAccessorImpl(AccessorConfiguration configuration) {
    super(configuration);
    id = new IdAccessorImpl(configuration);
    accounts = new AccountAccessorImpl(configuration);
  }

  @Override
  public AccountBaseAccessor accounts() {
    return accounts;
  }

  @Override
  public IdBaseAccessor id() {
    return id;
  }

}
