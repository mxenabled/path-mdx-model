package com.mx.web.mdx.models.Transfers;

import lombok.Data;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class RecurringTransferFrequencyListQueryParameters {
  private String flow;
  private String transfer_type;
}
