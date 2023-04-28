package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.account.AccountOwner;

/**
 * Accessor for account owner operations
 */
@GatewayClass
public abstract class AccountOwnerBaseAccessor extends Accessor {
  public AccountOwnerBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Gets the account owner
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Gets the account owner")
  public AccessorResponse<AccountOwner> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }
}
