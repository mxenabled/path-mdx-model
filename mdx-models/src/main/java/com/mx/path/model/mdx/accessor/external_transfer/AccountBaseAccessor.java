package com.mx.path.model.mdx.accessor.external_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.external_transfer.options.AccountListOptions;

/**
 * Accessor base for held accounts that can be used in an external transfer
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/external_transfer/#accounts-list-held-accounts")
public class AccountBaseAccessor extends Accessor {
  public AccountBaseAccessor() {
  }

  /**
   * List held accounts that can be used in an external transfer
   *
   * @param options
   * @return
   */
  @GatewayAPI
  @API(description = "List held accounts that can be used in an external transfer")
  public AccessorResponse<MdxList<Account>> list(AccountListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
