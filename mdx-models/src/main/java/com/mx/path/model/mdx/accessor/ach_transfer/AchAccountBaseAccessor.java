package com.mx.path.model.mdx.accessor.ach_transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.ach_transfer.AchAccount;
import com.mx.path.model.mdx.model.ach_transfer.options.AchAccountListOptions;

/**
 * Accessor base for ACH account operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#ach-account")
public abstract class AchAccountBaseAccessor extends Accessor {
  public AchAccountBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Creates an ACH account
   *
   * @param achAccount
   * @return AchAccount
   */
  @GatewayAPI
  @API(description = "Creates an ACH account")
  public AccessorResponse<AchAccount> create(AchAccount achAccount) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Deletes an ACH account
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Deletes an ACH account")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Gets an ACH account
   *
   * @param id
   * @return AchAccount
   */
  @GatewayAPI
  @API(description = "Gets an ACH account")
  public AccessorResponse<AchAccount> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Lists an ACH account according to search criteria
   *
   * @param options
   * @return MdxList&lt;AchAccount&gt;
   */
  @GatewayAPI
  @API(description = "List ACH Accounts according to search criteria")
  public AccessorResponse<MdxList<AchAccount>> list(AchAccountListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Updates an ACH account
   *
   * @param id
   * @param achAccount
   * @return AchAccount
   */
  @GatewayAPI
  @API(description = "Updates an ACH account")
  public AccessorResponse<AchAccount> update(String id, AchAccount achAccount) {
    throw new AccessorMethodNotImplementedException();
  }
}
