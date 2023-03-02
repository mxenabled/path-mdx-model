package com.mx.testing;

import com.mx.accessors.BaseAccessor;
import com.mx.accessors.remote_deposit.RemoteDepositBaseAccessor;
import com.mx.common.accessors.AccessorConfiguration;

public class TestAccessor extends BaseAccessor {
  public TestAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  public RemoteDepositBaseAccessor remoteDeposits() {
    return new RemoteDepositAccessor(getConfiguration());
  }
}
