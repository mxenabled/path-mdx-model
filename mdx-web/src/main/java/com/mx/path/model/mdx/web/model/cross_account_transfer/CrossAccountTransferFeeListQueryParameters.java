package com.mx.path.model.mdx.web.model.cross_account_transfer;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class CrossAccountTransferFeeListQueryParameters {
  private String account_type_id;
  private Integer account_type_number;
  private Number amount;
  private String destination_id;
  private String from_account_id;
}
