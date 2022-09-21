package com.mx.accessors.profile;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.profile.Phone;

/**
 * Accessor base for profile phones
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/profile/#phone-numbers">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#phone-numbers")
public class PhoneBaseAccessor extends Accessor {

  public PhoneBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a phone
   * @param phone
   * @return
   */
  @GatewayAPI
  @API(description = "Create a phone")
  public AccessorResponse<Phone> create(Phone phone) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete phone by id
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete a phone")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a phone
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a phone")
  public AccessorResponse<Phone> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List phones
   * @return
   */
  @GatewayAPI
  @API(description = "List phones")
  public AccessorResponse<MdxList<Phone>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a phone
   * @param phone
   * @return
   */
  @GatewayAPI
  @API(description = "Update a phone")
  public AccessorResponse<Phone> update(String id, Phone phone) {
    throw new AccessorMethodNotImplementedException();
  }

}
