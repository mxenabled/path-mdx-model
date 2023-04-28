package com.mx.testing;

import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.model.mdx.accessor.BaseAccessor;
import com.mx.path.model.mdx.accessor.account.AccountBaseAccessor;

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
