package com.mx.path.model.mdx.accessor.payment;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.payment.Payee;

/**
 * Accessor base for payee operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/payment/#payees">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment/#payees")
public abstract class PayeeBaseAccessor extends Accessor {

  public PayeeBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a payee
   * @param payee
   * @return
   */
  @GatewayAPI
  @API(description = "Create a payee")
  public AccessorResponse<Payee> create(Payee payee) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete a payee
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete a payee")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a payee
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a payee")
  public AccessorResponse<Payee> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all payee
   * @return
   */
  @GatewayAPI
  @API(description = "List all payee")
  public AccessorResponse<MdxList<Payee>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a payee
   * @param payee
   * @return
   */
  @GatewayAPI
  @API(description = "Update a payee")
  public AccessorResponse<Payee> update(String id, Payee payee) {
    throw new AccessorMethodNotImplementedException();
  }

}
