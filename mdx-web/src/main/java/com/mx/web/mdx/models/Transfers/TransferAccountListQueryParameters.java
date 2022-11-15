package com.mx.web.mdx.models.Transfers;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class TransferAccountListQueryParameters {
  private String flow;
  private String transfer_type;
  private String list_type;
  private String account_id;
}
