package com.mx.path.model.mdx.web.model.transfer;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class TransferAmountOptionsListQueryParameters {

  // NOTE: Non-standard naming to assist with parameter binding
  private String transfer_type;
  private String source_account_id;
  private String destination_account_id;
}
