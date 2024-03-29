package com.mx.path.model.mdx.accessor.transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.transfer.options.AccountListOptions;

/**
 * Accessor base for transfer account operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/index.html#transfer-accounts">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/index.html#transfer-accounts")
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
   * List account options
   *
   * @param options
   * @return
   */
  @GatewayAPI
  @API(description = "List account options using AccountSearch")
  public AccessorResponse<MdxList<Account>> list(AccountListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List from accounts
   *
   * @return
   */
  @Deprecated
  @GatewayAPI
  @API(description = "List from account options")
  public AccessorResponse<MdxList<Account>> fromAccounts() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List to accounts
   * Notes: Dependent on fromAccount selection
   *
   * @param fromAccountId
   * @return
   */
  @Deprecated
  @GatewayAPI
  @API(description = "List to account options")
  public AccessorResponse<MdxList<Account>> toAccounts(String fromAccountId) {
    throw new AccessorMethodNotImplementedException();
  }
}
