package com.mx.path.model.mdx.web.model.document;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class DocumentDeliveryGetQueryParameters {

  // NOTE: Non-standard naming to assist with parameter binding
  private String account_id;
  private String document_type;
}
