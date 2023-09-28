package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.StopPaymentReason;

/**
 * Accessor base for account stop payment reasons operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/index.html#accounts-stop-payment-list-stop-payment-reasons")
public abstract class AccountStopPaymentReasonsBaseAccessor extends Accessor {
  public AccountStopPaymentReasonsBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AccountStopPaymentReasonsBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Returns a list of stop payment reasons
   * @return
   */
  @GatewayAPI
  @API(description = "Provides a list of stop payment reasons")
  public AccessorResponse<MdxList<StopPaymentReason>> list() {
    throw new AccessorMethodNotImplementedException();
  }
}
