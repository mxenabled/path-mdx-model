package com.mx.accessors.transfer.recurring_transfer;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.transfer.RecurringTransfer;
import com.mx.models.transfer.options.RecurringTransferListOptions;

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
