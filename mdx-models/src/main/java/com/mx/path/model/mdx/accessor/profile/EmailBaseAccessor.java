package com.mx.path.model.mdx.accessor.profile;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.profile.Email;

/**
 * Accessor base for profile emails
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/profile/#email-addresses">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#email-addresses")
public class EmailBaseAccessor extends Accessor {
  public EmailBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public EmailBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create an email
   *
   * @param email
   * @return
   */
  @GatewayAPI
  @API(description = "Create an email address")
  public AccessorResponse<Email> create(Email email) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete email by id
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete an email")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get an email
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get an email address")
  public AccessorResponse<Email> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List emails
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List email addresses")
  public AccessorResponse<MdxList<Email>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update an email
   *
   * @param email
   * @return
   */
  @GatewayAPI
  @API(description = "Update an email address")
  public AccessorResponse<Email> update(String id, Email email) {
    throw new AccessorMethodNotImplementedException();
  }

}
