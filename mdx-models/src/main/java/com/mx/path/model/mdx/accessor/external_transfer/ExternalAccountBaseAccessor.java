package com.mx.path.model.mdx.accessor.external_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.external_transfer.ExternalAccount;
import com.mx.path.model.mdx.model.external_transfer.options.ExternalAccountListOptions;

/**
 * Accessor base for external account operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/external_transfer/#accounts")
public class ExternalAccountBaseAccessor extends Accessor {
  public ExternalAccountBaseAccessor() {
  }

  /**
   * Link an external account
   *
   * @param externalAccount
   * @return
   */
  @GatewayAPI
  @API(description = "Create an external account")
  public AccessorResponse<ExternalAccount> create(ExternalAccount externalAccount) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Remove a linked external account
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete an external account")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get an external account
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get an external account")
  public AccessorResponse<ExternalAccount> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List external accounts
   *
   * @param options
   * @return
   */
  @GatewayAPI
  @API(description = "List external accounts")
  public AccessorResponse<MdxList<ExternalAccount>> list(ExternalAccountListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update an external account
   *
   * @param id
   * @param externalAccount
   * @return
   */
  @GatewayAPI
  @API(description = "Update an external account")
  public AccessorResponse<ExternalAccount> update(String id, ExternalAccount externalAccount) {
    throw new AccessorMethodNotImplementedException();
  }
}
