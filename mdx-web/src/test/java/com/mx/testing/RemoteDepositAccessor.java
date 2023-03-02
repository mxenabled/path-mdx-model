package com.mx.testing;

import com.mx.accessors.remote_deposit.RemoteDepositBaseAccessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.account.Account;
import com.mx.models.remote_deposit.RemoteDeposit;

public class RemoteDepositAccessor extends RemoteDepositBaseAccessor {
  public RemoteDepositAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  public AccessorResponse<RemoteDeposit> create(RemoteDeposit remoteDeposit) {
    return super.create(remoteDeposit);
  }

  @Override
  public AccessorResponse<RemoteDeposit> get(String id) {
    return super.get(id);
  }

  @Override
  public AccessorResponse<MdxList<Account>> accounts() {
    return super.accounts();
  }

  @Override
  public AccessorResponse<MdxList<RemoteDeposit>> list() {
    return super.list();
  }
}
