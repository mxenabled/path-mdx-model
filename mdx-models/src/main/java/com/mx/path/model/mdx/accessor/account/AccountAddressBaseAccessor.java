package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.AccountAddress;

/**
 * Accessor base for account addresses
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/accounts/#addresses">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#addresses")
public class AccountAddressBaseAccessor extends Accessor {
  public AccountAddressBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AccountAddressBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create an account address
   *
   * @param address
   * @return
   */
  @GatewayAPI
  @API(description = "Create an account address")
  public AccessorResponse<AccountAddress> create(AccountAddress address) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete address by id
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete an account address")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get address by id
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get an account address")
  public AccessorResponse<AccountAddress> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List addresses
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all account addresses")
  public AccessorResponse<MdxList<AccountAddress>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update address
   *
   * @param id
   * @param address
   * @return
   */
  @GatewayAPI
  @API(description = "Update an account address")
  public AccessorResponse<AccountAddress> update(String id, AccountAddress address) {
    throw new AccessorMethodNotImplementedException();
  }

}
