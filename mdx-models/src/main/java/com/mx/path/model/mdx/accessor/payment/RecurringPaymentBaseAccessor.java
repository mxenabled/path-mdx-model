package com.mx.path.model.mdx.accessor.payment;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.payment.RecurringPayment;

/**
 * Accessor base for recurring payment operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/payment#recurring-payments">specifications</a>
 */

@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment#recurring-payments")
public abstract class RecurringPaymentBaseAccessor extends Accessor {

  public RecurringPaymentBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a recurring payment
   * @param recurringPayment
   * @return
   */
  @GatewayAPI
  @API(description = "Create a recurring payment")
  public AccessorResponse<RecurringPayment> create(RecurringPayment recurringPayment) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a recurring payment
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recurring payment")
  public AccessorResponse<RecurringPayment> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List frequency options for recurring payments
   * @return
   */
  @GatewayAPI
  @API(description = "List frequency options for recurring payments")
  public AccessorResponse<MdxList<Frequency>> frequencies() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all recurring payments
   * @return
   */
  @GatewayAPI
  @API(description = "List all recurring payments")
  public AccessorResponse<MdxList<RecurringPayment>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Cancel a recurring payment
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel a recurring payment")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update recurring payment
   * @param id
   * @param recurringPayment
   * @return
   */
  @GatewayAPI
  @API(description = "Update recurring payment")
  public AccessorResponse<RecurringPayment> update(String id, RecurringPayment recurringPayment) {
    throw new AccessorMethodNotImplementedException();
  }
}
