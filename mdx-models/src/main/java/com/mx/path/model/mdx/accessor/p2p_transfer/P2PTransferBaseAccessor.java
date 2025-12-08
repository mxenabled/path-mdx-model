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
import com.mx.path.model.mdx.model.p2p_transfer.P2PTransfer;

/**
 * Accessor base for p2p transfer operations
 *
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#p2p-transfers")
public class P2PTransferBaseAccessor extends Accessor {
  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AccountBaseAccessor accounts;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RecurringP2PTransferBaseAccessor recurring;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RecipientBaseAccessor recipients;

  public P2PTransferBaseAccessor() {
  }

  /**
   * Create a P2P transfer
   *
   * @param p2pTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Create a P2P transfer")
  public AccessorResponse<P2PTransfer> create(P2PTransfer p2pTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete a P2P transfer
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete a P2P transfer")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a P2P Transfer
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a P2P transfer")
  public AccessorResponse<P2PTransfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all P2P transfers
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all P2P transfers")
  public AccessorResponse<MdxList<P2PTransfer>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a P2P transfer
   *
   * @param id
   * @param p2pTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Update a P2P transfer")
  public AccessorResponse<P2PTransfer> update(String id, P2PTransfer p2pTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for account operations
   *
   * @return accessor
   */
  @API
  public AccountBaseAccessor accounts() {
    return accounts;
  }

  /**
   * Sets account accessor
   * @param accounts
   */
  public void setAccounts(AccountBaseAccessor accounts) {
    this.accounts = accounts;
  }

  /**
   * Accessor for recurring transfer operations
   *
   * @return accessor
   */
  @API
  public RecurringP2PTransferBaseAccessor recurring() {
    return recurring;
  }

  /**
   * Sets recurring transfer accessor
   * @param recurring
   */
  public void setRecurring(RecurringP2PTransferBaseAccessor recurring) {
    this.recurring = recurring;
  }

  /**
   * Accessor for recipient operations
   *
   * @return accessor
   */
  @API
  public RecipientBaseAccessor recipients() {
    return recipients;
  }

  /**
   * Sets recipient accessor
   * @param recipients
   */
  public void setRecipients(RecipientBaseAccessor recipients) {
    this.recipients = recipients;
  }
}
