package com.mx.testing;

import com.mx.accessors.account.AccountBaseAccessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.account.Account;

public class ChildAccessorAccount extends AccountBaseAccessor {
  public ChildAccessorAccount(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  public AccessorResponse<MdxList<Account>> list() {
    return super.list();
  }
}
