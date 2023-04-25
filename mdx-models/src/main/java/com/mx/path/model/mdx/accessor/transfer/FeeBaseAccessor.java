package com.mx.path.model.mdx.accessor.transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.remote.RemoteOperation;
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
