package com.mx.testing;

import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.accessor.remote_deposit.RemoteDepositBaseAccessor;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.remote_deposit.RemoteDeposit;

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
