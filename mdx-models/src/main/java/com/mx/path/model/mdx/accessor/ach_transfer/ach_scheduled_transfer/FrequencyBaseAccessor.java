package com.mx.path.model.mdx.accessor.ach_transfer.ach_scheduled_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.ach_transfer.options.FrequencyListOptions;

/**
 * Accessor for ACH scheduled transfer frequencies
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#ach-scheduled-transfers-scheduled-ach-transfer-frequencies")
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
   * List frequency options for ACH scheduled transfers
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List frequency options for ACH scheduled transfers")
  public AccessorResponse<MdxList<Frequency>> list(FrequencyListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
