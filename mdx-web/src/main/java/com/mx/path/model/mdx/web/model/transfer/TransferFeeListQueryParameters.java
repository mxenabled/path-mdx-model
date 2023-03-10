package com.mx.path.model.mdx.web.model.transfer;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class TransferFeeListQueryParameters {
  private Number amount;
  private String from_account_id;
  private String to_account_id;
}
