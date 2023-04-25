package com.mx.path.model.mdx.accessor.ach_transfer.ach_scheduled_transfer;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.ach_transfer.AchScheduledTransfer;
import com.mx.path.model.mdx.model.ach_transfer.options.AchScheduledTransferListOptions;

/**
 * Accessor base for ACH scheduled transfer operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#ach-scheduled-transfers")
public abstract class AchScheduledTransferBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private FrequencyBaseAccessor frequencies;

  public AchScheduledTransferBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Accessor for ACH scheduled transfer frequencies
   *
   * @return FrequencyBaseAccessor
   */
  @API(description = "List scheduled ACH transfer frequencies")
  public FrequencyBaseAccessor frequencies() {
    if (frequencies != null) {
      return frequencies;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set accessor for ACH scheduled transfer frequencies
   *
   * @param frequencies
   */
  public void setFrequencies(FrequencyBaseAccessor frequencies) {
    this.frequencies = frequencies;
  }

  /**
   * Cancels an ACH scheduled transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancels an ACH scheduled transfer")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Creates an ACH scheduled transfer
   *
   * @param achScheduledTransfer
   * @return AchScheduledTransfer
   */
  @GatewayAPI
  @API(description = "Creates an ACH scheduled transfer")
  public AccessorResponse<AchScheduledTransfer> create(AchScheduledTransfer achScheduledTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Gets an ACH scheduled transfer
   *
   * @param id
   * @return AchScheduledTransfer
   */
  @GatewayAPI
  @API(description = "Gets an ACH scheduled transfer")
  public AccessorResponse<AchScheduledTransfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all ACH scheduled transfers
   *
   * @return MdxList&lt;AchScheduledTransfer&gt;
   */
  @GatewayAPI
  @API(description = "List all ACH scheduled transfers")
  public AccessorResponse<MdxList<AchScheduledTransfer>> list(AchScheduledTransferListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Updates an ACH scheduled transfer
   *
   * @param id
   * @param achScheduledTransfer
   * @return AchScheduledTransfer
   */
  @GatewayAPI
  @API(description = "Updates an ACH scheduled transfer")
  public AccessorResponse<AchScheduledTransfer> update(String id, AchScheduledTransfer achScheduledTransfer) {
    throw new AccessorMethodNotImplementedException();
  }
}
