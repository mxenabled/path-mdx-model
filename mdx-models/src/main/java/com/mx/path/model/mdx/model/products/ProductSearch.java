package com.mx.path.model.mdx.model.products;

/**
 * Used to pass all search attributes to product search function.
 * Binds to incoming product search query string parameters
 */
@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
public class ProductSearch {

  private String type;

  // Non-standard naming (matches the `account_type` query parameter literally) because Spring's
  // default data binding for this controller method does not apply snake_case/camelCase
  // conversion. See TransactionSearchRequest.start_date for precedent.
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
