package com.mx.path.model.mdx.accessor.products;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.products.Product;
import com.mx.path.model.mdx.model.products.ProductSearch;

/**
 * Accessor base for product operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/product/#products">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/product/#products")
public abstract class ProductBaseAccessor extends Accessor {

  public ProductBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public ProductBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List all products
   *
   * @param productSearch
   * @return
   */
  @GatewayAPI
  @API(description = "List all products")
  public AccessorResponse<MdxList<Product>> list(ProductSearch productSearch) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Retrieve a product
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Retrieve a product")
  public AccessorResponse<Product> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a product
   *
   * @param id
   * @param product
   * @return
   */
  @GatewayAPI
  @API(description = "Update a product")
  public AccessorResponse<Product> update(String id, Product product) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a product - Version 20260529
   *
   * <p>Same request/response shape as {@link #update(String, Product)}; exists so
   * accessors can serve different challenge content (e.g. clients whose challenge-widget
   * rendering differs from the original consumer) based on client-sent API version, without
   * affecting consumers that don't request this version.
   *
   * @param id
   * @param product
   * @return
   */
  @GatewayAPI
  @API(description = "Update a product version 20260529")
  public AccessorResponse<Product> update20260529(String id, Product product) {
    throw new AccessorMethodNotImplementedException();
  }

}
