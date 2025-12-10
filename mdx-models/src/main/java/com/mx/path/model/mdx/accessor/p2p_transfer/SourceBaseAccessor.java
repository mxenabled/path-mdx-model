package com.mx.path.model.mdx.accessor.p2p_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.p2p_transfer.Source;

/**
 * Accessor base for P2P transfer source operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#sources")
public class SourceBaseAccessor extends Accessor {
  public SourceBaseAccessor() {
  }

  /**
   * Lists all p2p transfer sources
   *
   * @return MdxList&lt;Source&gt;
   */
  @GatewayAPI
  @API(description = "Lists all sources for P2P transfers")
  public AccessorResponse<MdxList<Source>> list() {
    throw new AccessorMethodNotImplementedException();
  }
}
