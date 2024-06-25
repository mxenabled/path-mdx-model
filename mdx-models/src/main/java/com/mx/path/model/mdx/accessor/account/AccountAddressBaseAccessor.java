package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.profile.Address;

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
   * @param accountId
   * @param address
   * @return
   */
  @GatewayAPI
  @API(description = "Create an account address")
  public AccessorResponse<Address> create(String accountId, Address address) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete address by id
   *
   * @param accountId
   * @param addressId
   * @return
   */
  @GatewayAPI
  @API(description = "Delete an account address")
  public AccessorResponse<Void> delete(String accountId, String addressId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get address by id
   *
   * @param accountId
   * @param addressId
   * @return
   */
  @GatewayAPI
  @API(description = "Get an account address")
  public AccessorResponse<Address> get(String accountId, String addressId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List addresses
   *
   * @param accountId
   * @return
   */
  @GatewayAPI
  @API(description = "List all account addresses")
  public AccessorResponse<MdxList<Address>> list(String accountId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update address
   *
   * @param accountId
   * @param addressId
   * @param address
   * @return
   */
  @GatewayAPI
  @API(description = "Update an account address")
  public AccessorResponse<Address> update(String accountId, String addressId, Address address) {
    throw new AccessorMethodNotImplementedException();
  }

}
