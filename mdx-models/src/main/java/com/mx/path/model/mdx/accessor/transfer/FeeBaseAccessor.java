package com.mx.path.model.mdx.accessor.transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.core.common.remote.RemoteOperation;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.Fee;
import com.mx.path.model.mdx.model.transfer.options.FeeListOptions;

/**
 * Accessor base for transfer fee operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/#transfers-transfer-fees">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/#transfers-transfer-fees")
public abstract class FeeBaseAccessor extends Accessor {

  public FeeBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Return a transfer's fees
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a transfer's fees")
  @RemoteOperation("listById")
  public AccessorResponse<MdxList<Fee>> list(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Return transfer fees based on options
   * @param options
   * @return
   */
  @GatewayAPI
  @API(description = "Get transfer fees based on options")
  public AccessorResponse<MdxList<Fee>> list(FeeListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
