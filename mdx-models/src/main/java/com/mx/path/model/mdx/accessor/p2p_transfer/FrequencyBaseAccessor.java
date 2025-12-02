package com.mx.path.model.mdx.accessor.p2p_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;

/**
 * Accessor base for p2p transfer frequency operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#frequencies")
public class FrequencyBaseAccessor extends Accessor {
  public FrequencyBaseAccessor() {
  }

  /**
   * List frequency options for p2p transfers
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List frequency options for p2p transfers")
  public AccessorResponse<MdxList<Frequency>> list() {
    throw new AccessorMethodNotImplementedException();
  }
}
