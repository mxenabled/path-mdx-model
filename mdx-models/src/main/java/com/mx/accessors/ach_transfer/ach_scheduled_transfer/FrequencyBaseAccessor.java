package com.mx.accessors.ach_transfer.ach_scheduled_transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.Frequency;
import com.mx.models.ach_transfer.options.FrequencyListOptions;

/**
 * Accessor for ACH scheduled transfer frequencies
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#ach-scheduled-transfers-scheduled-ach-transfer-frequencies")
public abstract class FrequencyBaseAccessor extends Accessor {

  public FrequencyBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List frequency options for ACH scheduled transfers
   * @return
   */
  @GatewayAPI
  @API(description = "List frequency options for ACH scheduled transfers")
  public AccessorResponse<MdxList<Frequency>> list(FrequencyListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }
}
