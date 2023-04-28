package com.mx.path.model.mdx.accessor.transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.TransferAmountOption;
import com.mx.path.model.mdx.model.transfer.options.TransferAmountOptionListOptions;

/**
 * Accessor base for transfer amount options
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/#transfer-amount-options">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/#transfer-amount-options")
public abstract class AmountOptionBaseAccessor extends Accessor {
  public AmountOptionBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List all transfer amount options
   *
   * @param options
   */
  @GatewayAPI
  @API(description = "List amount options")
  public AccessorResponse<MdxList<TransferAmountOption>> list(TransferAmountOptionListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
