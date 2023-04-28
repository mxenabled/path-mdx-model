package com.mx.path.model.mdx.accessor.payment;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
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
