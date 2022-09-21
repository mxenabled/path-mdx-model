package com.mx.testing;

import com.mx.accessors.BaseAccessor;
import com.mx.accessors.account.AccountBaseAccessor;
import com.mx.common.accessors.AccessorConfiguration;

public class BaseAccessorTestImplemented extends BaseAccessor {

  public class AccountBaseTestAccessor extends AccountBaseAccessor {
    AccountBaseTestAccessor(AccessorConfiguration configuration) {
      super(configuration);
    }
  }

  public BaseAccessorTestImplemented(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  public AccountBaseAccessor accounts() {
    return new AccountBaseTestAccessor(getConfiguration());
  }
}
