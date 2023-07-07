package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.Repayment;

/**
 * Accessor for account repayment operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#accounts-repayment-schedule")
public abstract class RepaymentBaseAccessor extends Accessor {
  public RepaymentBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public RepaymentBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get account repayments
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get account repayments")
  public AccessorResponse<MdxList<Repayment>> list(String id) {
    throw new AccessorMethodNotImplementedException();
  }
}
