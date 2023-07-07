package com.mx.path.model.mdx.accessor.profile;

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
 * Accessor base for profile addresses
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/profile/#addresses">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#addresses")
public class AddressBaseAccessor extends Accessor {
  public AddressBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AddressBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create an address
   *
   * @param address
   * @return
   */
  @GatewayAPI
  @API(description = "Create an address")
  public AccessorResponse<Address> create(Address address) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete address by id
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete an address")
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
  @API(description = "Get an address")
  public AccessorResponse<Address> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List addresses
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List addresses")
  public AccessorResponse<MdxList<Address>> list() {
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
  @API(description = "Update an address")
  public AccessorResponse<Address> update(String id, Address address) {
    throw new AccessorMethodNotImplementedException();
  }

}
