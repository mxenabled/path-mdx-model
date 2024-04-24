package com.mx.path.model.mdx.model.products;

/**
 * Used to pass all search attributes to product search function.
 * Binds to incoming product search query string parameters
 */
@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
public class ProductSearch {

  private String product_type;

  public final String getProduct_type() {
    return product_type;
  }

  public final void setProduct_type(String product_type) {
    this.product_type = product_type;
  }

}
