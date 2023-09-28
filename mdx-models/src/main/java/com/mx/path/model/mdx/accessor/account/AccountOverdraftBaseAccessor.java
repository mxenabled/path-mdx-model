package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.account.Overdraft;

/**
 * Accessor base for account overdraft operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/index.html#accounts-overdraft")
public abstract class AccountOverdraftBaseAccessor extends Accessor {
  public AccountOverdraftBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AccountOverdraftBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Gets overdraft values for a given account
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Retrieves overdraft values for a given account")
  public AccessorResponse<Overdraft> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Updates overdraft values for a given account
   * @param id
   * @param overdraft
   * @return
   */
  @GatewayAPI
  @API(description = "Updates the overdraft values for a given account")
  public AccessorResponse<Overdraft> update(String id, Overdraft overdraft) {
    throw new AccessorMethodNotImplementedException();
  }
}
