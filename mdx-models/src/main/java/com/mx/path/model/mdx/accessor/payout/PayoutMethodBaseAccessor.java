package com.mx.path.model.mdx.accessor.payout;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.payout.PayoutMethod;

/**
 * Accessor for payout method operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payout/#payout-methods")
public abstract class PayoutMethodBaseAccessor extends Accessor {
  public PayoutMethodBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public PayoutMethodBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create payout method
   *
   * @param recipientId
   * @param payoutMethod
   * @return
   */
  @GatewayAPI
  @API(description = "Create payout method")
  public AccessorResponse<PayoutMethod> create(String recipientId, PayoutMethod payoutMethod) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all payout methods
   *
   * @param recipientId
   * @return
   */
  @GatewayAPI
  @API(description = "List all payout methods")
  public AccessorResponse<MdxList<PayoutMethod>> list(String recipientId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a payout method
   *
   * @param recipientId
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a payout method")
  public AccessorResponse<PayoutMethod> get(String recipientId, String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a payout method
   *
   * @param recipientId
   * @param id
   * @param payoutMethod
   * @return
   */
  @GatewayAPI
  @API(description = "Update a payout method")
  public AccessorResponse<PayoutMethod> update(String recipientId, String id, PayoutMethod payoutMethod) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete a payout method
   *
   * @param recipientId
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete payout method")
  public AccessorResponse<Void> delete(String recipientId, String id) {
    throw new AccessorMethodNotImplementedException();
  }

}
