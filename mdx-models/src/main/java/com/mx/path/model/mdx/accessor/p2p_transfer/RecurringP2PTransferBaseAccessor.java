package com.mx.path.model.mdx.accessor.p2p_transfer;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.p2p_transfer.RecurringP2PTransfer;

/**
 * Accessor base for recurring P2P transfer operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#recurring-p2p-transfers")
public class RecurringP2PTransferBaseAccessor extends Accessor {
  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private FrequencyBaseAccessor frequencies;

  public RecurringP2PTransferBaseAccessor() {
  }

  /**
   * Create a recurring P2P transfer
   *
   * @param p2pTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Create a recurring P2P transfer")
  public AccessorResponse<RecurringP2PTransfer> create(RecurringP2PTransfer p2pTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Cancel a recurring P2P transfer
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel a recurring P2P transfer")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a recurring P2P Transfer
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recurring P2P transfer")
  public AccessorResponse<RecurringP2PTransfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all recurring P2P transfers
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all recurring P2P transfers")
  public AccessorResponse<MdxList<RecurringP2PTransfer>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a recurring P2P transfer
   *
   * @param id
   * @param p2pTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Update a recurring P2P transfer")
  public AccessorResponse<RecurringP2PTransfer> update(String id, RecurringP2PTransfer p2pTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for frequency operations
   *
   * @return accessor
   */
  @API
  public FrequencyBaseAccessor frequencies() {
    return frequencies;
  }

  /**
   * Sets frequency accessor
   * @param frequencies
   */
  public void setFrequencies(FrequencyBaseAccessor frequencies) {
    this.frequencies = frequencies;
  }
}
