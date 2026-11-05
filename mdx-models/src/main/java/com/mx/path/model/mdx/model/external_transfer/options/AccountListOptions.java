package com.mx.path.model.mdx.model.external_transfer.options;

import lombok.Data;

/**
 * Filters for listing held accounts that can be used in an external transfer.
 */
@Data
public class AccountListOptions {
  private String accountId;
  private String externalAccountId;
  private String listType;
}
