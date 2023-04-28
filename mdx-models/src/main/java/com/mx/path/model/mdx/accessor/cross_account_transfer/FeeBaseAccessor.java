package com.mx.path.model.mdx.accessor.cross_account_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.cross_account_transfer.options.FeeListOptions;
import com.mx.path.model.mdx.model.transfer.Fee;

/**
 * Accessor base for cross account transfer fee operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/cross_account_transfer/#fees-list-cross-account-transfer-fees">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/cross_account_transfer/#fees-list-cross-account-transfer-fees")
public abstract class FeeBaseAccessor extends Accessor {

  public FeeBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List fees for cross account transfers
   * @return
   */
  @GatewayAPI
  @API(description = "List all fees for cross account transfers")
  public AccessorResponse<MdxList<Fee>> list(FeeListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
