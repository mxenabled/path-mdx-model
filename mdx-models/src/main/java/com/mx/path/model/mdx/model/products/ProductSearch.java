package com.mx.path.model.mdx.model.products;

/**
 * Used to pass all search attributes to product search function.
 * Binds to incoming product search query string parameters
 */
@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
public class ProductSearch {

  private String type;
  private String account_type;

  public final String getType() {
    return type;
  }

  public final void setType(String type) {
    this.type = type;
  }

  public final String getAccount_type() {
    return account_type;
  }

  public final void setAccount_type(String account_type) {
    this.account_type = account_type;
  }

}
