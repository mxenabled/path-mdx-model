package com.mx.accessors.account;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.transfer.Repayment;

/**
 * Accessor for account repayment operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#accounts-repayment-schedule")
public abstract class RepaymentBaseAccessor extends Accessor {
  public RepaymentBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get account repayments
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get account repayments")
  public AccessorResponse<MdxList<Repayment>> list(String id) {
    throw new AccessorMethodNotImplementedException();
  }
}
