package com.mx.path.model.mdx.accessor.payment;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.payment.Bill;

/**
 * Accessor base for bill operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/payment/#bills">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment/#bills")
public abstract class BillBaseAccessor extends Accessor {

  public BillBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a bill
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a bill")
  public AccessorResponse<Bill> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all bills
   * @return
   */
  @GatewayAPI
  @API(description = "List all bills")
  public AccessorResponse<MdxList<Bill>> list() {
    throw new AccessorMethodNotImplementedException();
  }

}
