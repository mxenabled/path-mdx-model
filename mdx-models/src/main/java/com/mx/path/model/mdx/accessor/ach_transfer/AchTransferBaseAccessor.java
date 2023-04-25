package com.mx.path.model.mdx.accessor.ach_transfer;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.accessor.ach_transfer.ach_scheduled_transfer.AchScheduledTransferBaseAccessor;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.ach_transfer.AchTransfer;
import com.mx.path.model.mdx.model.ach_transfer.options.AchTransferListOptions;

/**
 * Accessor base for ACH transfer operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#transfers")
public abstract class AchTransferBaseAccessor extends Accessor {
  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AccountBaseAccessor accounts;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AchAccountBaseAccessor achAccounts;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AchScheduledTransferBaseAccessor scheduled;

  public AchTransferBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Account accessor
   *
   * @return AccountBaseAccessor
   */
  @API(description = "Access held accounts")
  public AccountBaseAccessor accounts() {
    if (accounts != null) {
      return accounts;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * ACH account accessor
   *
   * @return AchAccountBaseAccessor
   */
  @API(description = "Access ACH accounts")
  public AchAccountBaseAccessor achAccounts() {
    if (achAccounts != null) {
      return achAccounts;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set account accessor
   * @param accounts
   */
  public void setAccounts(AccountBaseAccessor accounts) {
    this.accounts = accounts;
  }

  /**
   * Set account accessor
   *
   * @param achAccounts
   */
  public void setAchAccounts(AchAccountBaseAccessor achAccounts) {
    this.achAccounts = achAccounts;
  }

  /**
   * Cancels an ACH transfer
   *
   * @param id
   * @return void
   */
  @GatewayAPI
  @API(description = "Cancels an ACH transfer")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Creates an ACH transfer
   *
   * @param achTransfer
   * @return AchTransfer
   */
  @GatewayAPI
  @API(description = "Creates an ACH transfer")
  public AccessorResponse<AchTransfer> create(AchTransfer achTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Gets an ACH transfer
   *
   * @param id
   * @return AchTransfer
   */
  @GatewayAPI
  @API(description = "Gets an ACH transfer")
  public AccessorResponse<AchTransfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all ACH transfers
   *
   * @return MdxList&lt;AchTransfer&gt;
   */
  @GatewayAPI
  @API(description = "List all ACH transfers")
  public AccessorResponse<MdxList<AchTransfer>> list(AchTransferListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Updates an ACH transfer
   *
   * @param id
   * @param achTransfer
   * @return AchTransfer
   */
  @GatewayAPI
  @API(description = "Updates an ACH transfer")
  public AccessorResponse<AchTransfer> update(String id, AchTransfer achTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for scheduled ACH transfer operations
   *
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#ach-scheduled-transfers")
  public AchScheduledTransferBaseAccessor scheduled() {
    if (scheduled != null) {
      return scheduled;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set ACH scheduled transfer accessor
   *
   * @param scheduled
   */
  public void setScheduled(AchScheduledTransferBaseAccessor scheduled) {
    this.scheduled = scheduled;
  }
}
