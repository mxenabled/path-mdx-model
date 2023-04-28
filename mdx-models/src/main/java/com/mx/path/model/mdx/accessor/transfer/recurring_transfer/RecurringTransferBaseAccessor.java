package com.mx.path.model.mdx.accessor.transfer.recurring_transfer;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.RecurringTransfer;
import com.mx.path.model.mdx.model.transfer.options.RecurringTransferListOptions;

/**
 * Accessor base for recurring transfer operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/#recurring-transfers">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/#recurring-transfers")
public abstract class RecurringTransferBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private FrequencyBaseAccessor frequencies;

  public RecurringTransferBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a recurring transfer
   * @param recurringTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Create a recurring transfer")
  public AccessorResponse<RecurringTransfer> create(RecurringTransfer recurringTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete a recurring transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete a recurring transfer")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a recurring transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recurring transfer")
  public AccessorResponse<RecurringTransfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List recurring transfers
   * @return
   */
  @GatewayAPI
  @API(description = "List recurring transfers")
  public AccessorResponse<MdxList<RecurringTransfer>> list(RecurringTransferListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Skip next payment of recurring transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Skip next occurrence of recurring transfer")
  public AccessorResponse<RecurringTransfer> skipNext(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a recurring transfer
   * @param id
   * @param recurringTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Update recurring transfer")
  public AccessorResponse<RecurringTransfer> update(String id, RecurringTransfer recurringTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for recurring transfer frequency operations
   * @return accessor
   */
  @API(description = "Access recurring transfer frequencies")
  public FrequencyBaseAccessor frequencies() {
    if (frequencies != null) {
      return frequencies;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set recurring transfer frequencies accessor
   * @param frequencies
   */
  public void setFrequencies(FrequencyBaseAccessor frequencies) {
    this.frequencies = frequencies;
  }
}
