package com.mx.accessors.transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.transfer.Repayment;
import com.mx.models.transfer.options.RepaymentListOptions;

/**
 * Accessor base for transfer repayment operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/#transfers-transfer-repayment-schedule">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/#transfers-transfer-repayment-schedule")
public abstract class RepaymentBaseAccessor extends Accessor {

  public RepaymentBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Return a transfer's repayment schedule
   * @param id
   * @param options
   * @return
   */
  @GatewayAPI
  @API(description = "Get a transfer's repayment schedule")
  public AccessorResponse<MdxList<Repayment>> list(String id, RepaymentListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
