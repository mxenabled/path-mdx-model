package com.mx.path.model.mdx.accessor.cross_account_transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.cross_account_transfer.DestinationAccount;

/**
 * Accessor base for destination accounts in cross accounts transfer operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/cross_account_transfer/#destinations">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/cross_account_transfer/#destinations")
public class DestinationBaseAccessor extends Accessor {
  public DestinationBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a Destination account
   * @param destinationAccount
   * @return
   */
  @GatewayAPI
  @API(description = "Create a Destination account")
  public AccessorResponse<DestinationAccount> create(DestinationAccount destinationAccount) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete a destination account
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "delete the destination account")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a destination account information
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recurring cross account transfer")
  public AccessorResponse<DestinationAccount> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all destination accounts
   * @return
   */
  @GatewayAPI
  @API(description = "List all destination accounts")
  public AccessorResponse<MdxList<DestinationAccount>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update destination account
   * @param id
   * @param destinationAccount
   * @return
   */
  @GatewayAPI
  @API(description = "Update destination account")
  public AccessorResponse<DestinationAccount> update(String id, DestinationAccount destinationAccount) {
    throw new AccessorMethodNotImplementedException();
  }
}
