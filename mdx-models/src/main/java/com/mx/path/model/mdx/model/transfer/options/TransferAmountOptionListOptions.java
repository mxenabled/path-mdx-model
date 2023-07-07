package com.mx.path.model.mdx.model.transfer.options;

import lombok.Data;

/**
 * Used for encapsulating the request parameters for a TransferAmountOption#list request.
 * <p>
 * https://developer.mx.com/drafts/mdx/transfer/index.html#transfer-amount-options
 */
@Data
public class TransferAmountOptionListOptions {
  private String transferType;
  private String sourceAccountId;
  private String destinationAccountId;
}
