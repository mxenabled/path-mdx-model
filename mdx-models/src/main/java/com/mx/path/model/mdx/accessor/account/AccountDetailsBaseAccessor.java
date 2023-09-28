package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.account.AccountDetails;

/**
 * Accessor base for account details operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/index.html#accounts-account-details-additional")
public abstract class AccountDetailsBaseAccessor extends Accessor {
  public AccountDetailsBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AccountDetailsBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Gets additional details for a given account
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get additional details about an account that aren't supported by the MX platform")
  public AccessorResponse<AccountDetails> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }
}
