package com.mx.path.model.mdx.accessor.payout;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.payout.Recipient;

/**
 * Accessor for recipient operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payout/#recipients")
public abstract class RecipientBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private PayoutMethodBaseAccessor payoutMethods;

  public RecipientBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a recipient
   * @param recipient
   * @return
   */
  @GatewayAPI
  @API(description = "Create a recipient")
  public AccessorResponse<Recipient> create(Recipient recipient) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all recipients
   * @param includePayoutMethods
   * @return
   */
  @GatewayAPI
  @API(description = "List all recipients")
  public AccessorResponse<MdxList<Recipient>> list(boolean includePayoutMethods) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a recipient
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recipient")
  public AccessorResponse<Recipient> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a recipient
   * @param id
   * @param recipient
   * @return
   */
  @GatewayAPI
  @API(description = "Update a recipient")
  public AccessorResponse<Recipient> update(String id, Recipient recipient) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete a recipient
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete a recipient")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for payout method operations
   * @return accessor
   */
  @API
  public PayoutMethodBaseAccessor payoutMethods() {
    if (payoutMethods != null) {
      return payoutMethods;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set payout method accessor
   * @param payoutMethods
   */
  public void setPayoutMethods(PayoutMethodBaseAccessor payoutMethods) {
    this.payoutMethods = payoutMethods;
  }
}
