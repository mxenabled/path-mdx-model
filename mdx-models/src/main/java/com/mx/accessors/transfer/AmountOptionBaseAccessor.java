package com.mx.accessors.transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.transfer.TransferAmountOption;
import com.mx.models.transfer.options.TransferAmountOptionListOptions;

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
