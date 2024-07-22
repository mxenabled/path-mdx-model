package com.mx.path.model.mdx.model.products;

/**
 * Used to pass all search attributes to product search function.
 * Binds to incoming product search query string parameters
 */
@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
public class ProductSearch {

  private String type;

  public final String getType() {
    return type;
  }

  public final void setType(String type) {
    this.type = type;
  }

}
