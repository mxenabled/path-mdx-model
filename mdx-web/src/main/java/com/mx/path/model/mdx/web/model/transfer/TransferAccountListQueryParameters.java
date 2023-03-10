package com.mx.path.model.mdx.web.model.transfer;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class TransferAccountListQueryParameters {
  private String flow;
  private String transfer_type;
  private String list_type;
  private String account_id;
}
