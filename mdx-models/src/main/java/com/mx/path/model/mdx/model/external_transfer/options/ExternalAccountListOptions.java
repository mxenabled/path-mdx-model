package com.mx.path.model.mdx.model.external_transfer.options;

import lombok.Data;

/**
 * Filters for listing external accounts.
 */
@Data
public class ExternalAccountListOptions {
  private String accountId;
  private String externalAccountId;
  private String listType;
}
