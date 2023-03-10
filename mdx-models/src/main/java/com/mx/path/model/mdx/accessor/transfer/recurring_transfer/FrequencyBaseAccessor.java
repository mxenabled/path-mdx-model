package com.mx.path.model.mdx.accessor.transfer.recurring_transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.transfer.options.FrequencyListOptions;

/**
 * Accessor base for recurring transfer frequency operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/index.html#recurring-transfer-frequencies">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/index.html#recurring-transfer-frequencies")
public abstract class FrequencyBaseAccessor extends Accessor {

  public FrequencyBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List frequency options for recurring transfers
   * @param options
   * @return
   */
  @GatewayAPI
  @API(description = "List frequency options for recurring transfers")
  public AccessorResponse<MdxList<Frequency>> list(FrequencyListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
