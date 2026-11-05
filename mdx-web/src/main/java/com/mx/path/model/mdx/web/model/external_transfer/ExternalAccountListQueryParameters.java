package com.mx.path.model.mdx.web.model.external_transfer;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class ExternalAccountListQueryParameters {

  // NOTE: Non-standard naming to assist with parameter binding
  private String account_id;
  private String external_account_id;
  private String list_type;
}
