package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.check.CheckImage;
import com.mx.path.model.mdx.model.check.options.CheckImageGetOptions;

/**
 * Accessor for check image operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#check-images")
public abstract class CheckImageBaseAccessor extends Accessor {
  public CheckImageBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public CheckImageBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a check image
   *
   * @param accountId
   * @param transactionId
   * @param checkNumber
   * @param options
   * @return
   */
  @GatewayAPI
  @API(description = "Get a check image")
  public AccessorResponse<CheckImage> get(String accountId, String transactionId, String checkNumber, CheckImageGetOptions options) {
    throw new AccessorMethodNotImplementedException();
  }

}
