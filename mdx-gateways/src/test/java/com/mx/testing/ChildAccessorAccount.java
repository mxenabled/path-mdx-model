package com.mx.testing;

import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.accessor.account.AccountBaseAccessor;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;

public class ChildAccessorAccount extends AccountBaseAccessor {
  public ChildAccessorAccount(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  public AccessorResponse<MdxList<Account>> list() {
    return super.list();
  }
}
