package com.mx.web.mdx.models.AchTransfers;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class AchTransferAchAccountListQueryParameters {

  // NOTE: Non-standard naming to assist with parameter binding
  private String transfer_type;
  private String list_type;
  private String account_id;
  private String achAccount_id;
}
