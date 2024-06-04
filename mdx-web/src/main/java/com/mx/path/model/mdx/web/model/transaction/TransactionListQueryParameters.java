package com.mx.path.model.mdx.web.model.transaction;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class TransactionListQueryParameters {
  private String check_number;
  private String status;
}
