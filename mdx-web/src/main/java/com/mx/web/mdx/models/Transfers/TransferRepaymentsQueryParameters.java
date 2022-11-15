package com.mx.web.mdx.models.Transfers;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class TransferRepaymentsQueryParameters {

  // NOTE: Non-standard naming to assist with parameter binding
  private String start_on;
  private String transfer_type;
  private String frequency_id;
}
