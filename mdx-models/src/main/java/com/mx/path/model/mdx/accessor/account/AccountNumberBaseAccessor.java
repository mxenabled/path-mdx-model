package com.mx.path.model.mdx.accessor.account;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.account.AccountNumbers;

/**
 * Accessor for account number operations
 */
@GatewayClass
@API(description = "Access to user account numbers")
public abstract class AccountNumberBaseAccessor extends Accessor {
  public AccountNumberBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get account numbers
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get account numbers")
  public AccessorResponse<AccountNumbers> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }
}
