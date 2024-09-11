package com.mx.path.model.mdx.accessor.managed_card;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.managed_cards.Merchant;

/**
 * Accessor base for managed card merchants
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/managed_cards/#merchants")
public class MerchantBaseAccessor extends Accessor {

  /**
   * List all merchants
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all merchants")
  public AccessorResponse<MdxList<Merchant>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a merchant by id
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a merchant by id")
  public AccessorResponse<Merchant> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update merchant
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "update a merchant")
  public AccessorResponse<Merchant> update(String id, Merchant merchant) {
    throw new AccessorMethodNotImplementedException();
  }
}
