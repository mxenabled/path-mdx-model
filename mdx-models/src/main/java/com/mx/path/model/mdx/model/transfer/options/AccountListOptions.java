package com.mx.path.model.mdx.model.transfer.options;

import lombok.Data;

@Data
public class AccountListOptions {
  private String accountId;
  @Deprecated
  private String flow;
  private String listType;
  private String transferType;
}
