package com.mx.path.model.mdx.accessor.external_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;

/**
 * Accessor base for recurring external transfer frequency operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/external_transfer/#frequencies")
public class FrequencyBaseAccessor extends Accessor {
  public FrequencyBaseAccessor() {
  }

  /**
   * List frequencies that can be used to create or update a recurring external transfer
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List frequency options for recurring external transfers")
  public AccessorResponse<MdxList<Frequency>> list() {
    throw new AccessorMethodNotImplementedException();
  }
}
