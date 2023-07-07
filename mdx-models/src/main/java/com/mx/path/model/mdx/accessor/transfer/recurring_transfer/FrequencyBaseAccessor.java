package com.mx.path.model.mdx.accessor.transfer.recurring_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.options.FrequencyListOptions;

/**
 * Accessor base for recurring transfer frequency operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/index.html#recurring-transfer-frequencies">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/index.html#recurring-transfer-frequencies")
public abstract class FrequencyBaseAccessor extends Accessor {
  public FrequencyBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public FrequencyBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List frequency options for recurring transfers
   *
   * @param options
   * @return
   */
  @GatewayAPI
  @API(description = "List frequency options for recurring transfers")
  public AccessorResponse<MdxList<Frequency>> list(FrequencyListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
