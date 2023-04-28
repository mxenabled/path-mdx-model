package com.mx.path.model.mdx.accessor.payment;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.payment.Merchant;
import com.mx.path.model.mdx.model.payment.MerchantCategory;

/**
 * Accessor base for merchant operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/payment/#merchants">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment/#merchants")
public abstract class MerchantBaseAccessor extends Accessor {

  public MerchantBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List all merchants
   * @param name
   * @return
   */
  @GatewayAPI
  @API(description = "List all merchants")
  public AccessorResponse<MdxList<Merchant>> list(String name) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Search merchants by category
   * @param merchantCategoryId
   * @return
   */
  @GatewayAPI
  @API(description = "List all merchants")
  public AccessorResponse<MdxList<Merchant>> search(String merchantCategoryId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all merchant categories
   * @return
   */
  @GatewayAPI
  @API(description = "List all merchant categories")
  public AccessorResponse<MdxList<MerchantCategory>> categories() {
    throw new AccessorMethodNotImplementedException();
  }

}
