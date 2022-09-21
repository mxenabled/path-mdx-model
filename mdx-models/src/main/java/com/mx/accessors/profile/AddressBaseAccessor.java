package com.mx.accessors.profile;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.profile.Address;

/**
 * Accessor base for profile addresses
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/profile/#addresses">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#addresses")
public class AddressBaseAccessor extends Accessor {

  public AddressBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create an address
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
   * @return
   */
  @GatewayAPI
  @API(description = "List addresses")
  public AccessorResponse<MdxList<Address>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update address
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
