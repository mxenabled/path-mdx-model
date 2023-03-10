package com.mx.testing;

import com.mx.common.accessors.AccessorConfiguration;
import com.mx.path.model.mdx.accessor.BaseAccessor;
import com.mx.path.model.mdx.accessor.remote_deposit.RemoteDepositBaseAccessor;

public class TestAccessor extends BaseAccessor {
  public TestAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  public RemoteDepositBaseAccessor remoteDeposits() {
    return new RemoteDepositAccessor(getConfiguration());
  }
}
