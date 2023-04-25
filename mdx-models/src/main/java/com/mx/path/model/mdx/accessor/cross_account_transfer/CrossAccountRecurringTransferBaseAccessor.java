package com.mx.path.model.mdx.accessor.cross_account_transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.cross_account_transfer.CrossAccountRecurringTransfer;

/**
 * Accessor base for recurring cross account transfer operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/cross_account_transfer/#recurring-cross-account-transfers">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/cross_account_transfer/#recurring-cross-account-transfers")
public class CrossAccountRecurringTransferBaseAccessor extends Accessor {
  public CrossAccountRecurringTransferBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a recurring cross account transfer
   * @param crossAccountRecurringTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Create a recurring cross account transfer")
  public AccessorResponse<CrossAccountRecurringTransfer> create(CrossAccountRecurringTransfer crossAccountRecurringTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete a recurring cross account transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "delete the recurring cross account transfer")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a recurring cross account transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recurring cross account transfer")
  public AccessorResponse<CrossAccountRecurringTransfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all recurring cross account transfers
   * @return
   */
  @GatewayAPI
  @API(description = "List all recurring cross account transfers")
  public AccessorResponse<MdxList<CrossAccountRecurringTransfer>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Skip next payment of recurring cross account transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Skip next occurrence of recurring cross account transfer")
  public AccessorResponse<CrossAccountRecurringTransfer> skipNext(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update recurring cross account transfer
   * @param id
   * @param crossAccountRecurringTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Update recurring cross account transfer")
  public AccessorResponse<CrossAccountRecurringTransfer> update(String id, CrossAccountRecurringTransfer crossAccountRecurringTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

}
