package com.mx.path.model.mdx.accessor.external_transfer;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.external_transfer.RecurringExternalTransfer;

/**
 * Accessor base for recurring external transfer operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/external_transfer/#recurring-external-transfers")
public class RecurringExternalTransferBaseAccessor extends Accessor {
  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private FrequencyBaseAccessor frequencies;

  public RecurringExternalTransferBaseAccessor() {
  }

  /**
   * Cancel a recurring external transfer and all of its future transfers
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel a recurring external transfer")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create a recurring external transfer
   *
   * @param recurringExternalTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Create a recurring external transfer")
  public AccessorResponse<RecurringExternalTransfer> create(RecurringExternalTransfer recurringExternalTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a recurring external transfer
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recurring external transfer")
  public AccessorResponse<RecurringExternalTransfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all recurring external transfers. Only series are returned, not the transfers they create.
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all recurring external transfers")
  public AccessorResponse<MdxList<RecurringExternalTransfer>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a recurring external transfer. Changes apply to all future transfers it creates.
   *
   * @param id
   * @param recurringExternalTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Update a recurring external transfer")
  public AccessorResponse<RecurringExternalTransfer> update(String id, RecurringExternalTransfer recurringExternalTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for frequency operations
   *
   * @return accessor
   */
  @API
  public FrequencyBaseAccessor frequencies() {
    if (frequencies != null) {
      return frequencies;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Sets frequency accessor
   *
   * @param frequencies
   */
  public void setFrequencies(FrequencyBaseAccessor frequencies) {
    this.frequencies = frequencies;
  }
}
