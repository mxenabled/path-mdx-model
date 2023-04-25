package com.mx.path.model.mdx.accessor.payout;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.payout.PayoutMethod;

/**
 * Accessor for payout method operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payout/#payout-methods")
public abstract class PayoutMethodBaseAccessor extends Accessor {

  public PayoutMethodBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create payout method
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
