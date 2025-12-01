package com.mx.path.model.mdx.accessor.p2p_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.p2p_transfer.Duration;

/**
 * Accessor base for P2P transfer duration operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#durations")
public class DurationBaseAccessor extends Accessor {
  public DurationBaseAccessor() {
  }

  /**
   * List all P2P transfer durations
   *
   * @return MdxList&lt;Duration&gt;
   */
  @GatewayAPI
  @API(description = "List all durations for P2P transfers")
  public AccessorResponse<MdxList<Duration>> list() {
    throw new AccessorMethodNotImplementedException();
  }
}
