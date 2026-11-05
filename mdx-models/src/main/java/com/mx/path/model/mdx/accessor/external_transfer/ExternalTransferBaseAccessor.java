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
import com.mx.path.model.mdx.model.external_transfer.ExternalTransfer;

/**
 * Accessor base for external transfer operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/external_transfer/#external-transfers")
public class ExternalTransferBaseAccessor extends Accessor {
  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AccountBaseAccessor accounts;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private ExternalAccountBaseAccessor externalAccounts;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RecurringExternalTransferBaseAccessor recurring;

  public ExternalTransferBaseAccessor() {
  }

  /**
   * Cancel a pending external transfer
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel an external transfer")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create an external transfer
   *
   * @param externalTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Create an external transfer")
  public AccessorResponse<ExternalTransfer> create(ExternalTransfer externalTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get an external transfer
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get an external transfer")
  public AccessorResponse<ExternalTransfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all external transfers, including one pending transfer for the next occurrence of each active recurring
   * external transfer
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all external transfers")
  public AccessorResponse<MdxList<ExternalTransfer>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a pending external transfer
   *
   * @param id
   * @param externalTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Update an external transfer")
  public AccessorResponse<ExternalTransfer> update(String id, ExternalTransfer externalTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for held account operations
   *
   * @return accessor
   */
  @API
  public AccountBaseAccessor accounts() {
    return accounts;
  }

  /**
   * Sets held account accessor
   *
   * @param accounts
   */
  public void setAccounts(AccountBaseAccessor accounts) {
    this.accounts = accounts;
  }

  /**
   * Accessor for external account operations
   *
   * @return accessor
   */
  @API
  public ExternalAccountBaseAccessor externalAccounts() {
    return externalAccounts;
  }

  /**
   * Sets external account accessor
   *
   * @param externalAccounts
   */
  public void setExternalAccounts(ExternalAccountBaseAccessor externalAccounts) {
    this.externalAccounts = externalAccounts;
  }

  /**
   * Accessor for recurring external transfer operations
   *
   * @return accessor
   */
  @API
  public RecurringExternalTransferBaseAccessor recurring() {
    return recurring;
  }

  /**
   * Sets recurring external transfer accessor
   *
   * @param recurring
   */
  public void setRecurring(RecurringExternalTransferBaseAccessor recurring) {
    this.recurring = recurring;
  }
}
