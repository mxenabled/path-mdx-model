package com.mx.testing;

import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.configuration.annotations.ChildAccessor;
import com.mx.path.model.mdx.accessor.BaseAccessor;
import com.mx.path.model.mdx.accessor.account.AccountBaseAccessor;

@ChildAccessor(ChildAccessorAccount.class)
public class ChildAccessorBase extends BaseAccessor {
  public ChildAccessorBase(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  public AccountBaseAccessor accounts() {
    return super.accounts();
  }
}
