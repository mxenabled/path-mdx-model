package com.mx.path.model.mdx.accessor.ach_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.ach_transfer.options.AccountListOptions;

/**
 * Accessor base for ACH held-account operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#ach-account-list-held-accounts")
public abstract class AccountBaseAccessor extends Accessor {
  public AccountBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AccountBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Lists held accounts according to search criteria
   *
   * @param options
   * @return MdxList&lt;Account&gt;
   */
  @GatewayAPI
  @API(description = "Lists accounts according to search criteria")
  public AccessorResponse<MdxList<Account>> list(AccountListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
