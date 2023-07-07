package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.account.AccountNumbers;

/**
 * Accessor for account number operations
 */
@GatewayClass
@API(description = "Access to user account numbers")
public abstract class AccountNumberBaseAccessor extends Accessor {
  public AccountNumberBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AccountNumberBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get account numbers
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get account numbers")
  public AccessorResponse<AccountNumbers> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }
}
