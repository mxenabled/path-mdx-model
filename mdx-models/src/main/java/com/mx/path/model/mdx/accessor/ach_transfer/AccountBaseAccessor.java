package com.mx.path.model.mdx.accessor.ach_transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.ach_transfer.options.AccountListOptions;

/**
 * Accessor base for ACH held-account operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#ach-account-list-held-accounts")
public abstract class AccountBaseAccessor extends Accessor {
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
