package com.mx.path.model.mdx.accessor.cross_account_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;

/**
 * Accessor base for cross account transfer frequency operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/cross_account_transfer/#frequencies">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/cross_account_transfer/#frequencies")
public abstract class FrequencyBaseAccessor extends Accessor {

  public FrequencyBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List frequency options for cross account transfers
   * @return
   */
  @GatewayAPI
  @API(description = "List frequency options for cross account transfers")
  public AccessorResponse<MdxList<Frequency>> list() {
    throw new AccessorMethodNotImplementedException();
  }
}
