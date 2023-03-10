package com.mx.path.model.mdx.accessor.cross_account_transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.Frequency;

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
