package com.mx.accessors.payment;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.payment.Merchant;
import com.mx.models.payment.MerchantCategory;

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
