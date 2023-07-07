package com.mx.path.model.mdx.accessor.transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.Repayment;
import com.mx.path.model.mdx.model.transfer.options.RepaymentListOptions;

/**
 * Accessor base for transfer repayment operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/#transfers-transfer-repayment-schedule">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/#transfers-transfer-repayment-schedule")
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
   * Return a transfer's repayment schedule
   *
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
