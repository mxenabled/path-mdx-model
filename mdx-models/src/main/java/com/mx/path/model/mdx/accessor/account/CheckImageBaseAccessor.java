package com.mx.path.model.mdx.accessor.account;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.check.CheckImage;
import com.mx.path.model.mdx.model.check.options.CheckImageGetOptions;

/**
 * Accessor for check image operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#check-images")
public abstract class CheckImageBaseAccessor extends Accessor {

  public CheckImageBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a check image
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
