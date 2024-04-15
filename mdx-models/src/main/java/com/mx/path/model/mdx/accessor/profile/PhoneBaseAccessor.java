package com.mx.path.model.mdx.accessor.profile;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.profile.Phone;

/**
 * Accessor base for profile phones
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/profile/#phone-numbers">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#phone-numbers")
public class PhoneBaseAccessor extends Accessor {
  public PhoneBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public PhoneBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a phone
   *
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
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete a phone")
  public AccessorResponse<Phone> delete(String id, Phone phone) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a phone
   *
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
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List phones")
  public AccessorResponse<MdxList<Phone>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a phone
   *
   * @param phone
   * @return
   */
  @GatewayAPI
  @API(description = "Update a phone")
  public AccessorResponse<Phone> update(String id, Phone phone) {
    throw new AccessorMethodNotImplementedException();
  }

}
