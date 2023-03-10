package com.mx.path.model.mdx.accessor.account;

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
import com.mx.path.model.mdx.model.dispute.Dispute;

/**
 * Accessor for account operations
 */
@GatewayClass
@API(description = "Access to user disputes", specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#disputes")
public abstract class DisputeBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private DisputedTransactionBaseAccessor disputedTransactions;

  public DisputeBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a dispute by id
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a dispute by id")
  public AccessorResponse<Dispute> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get all disputes
   * @return
   */
  @GatewayAPI
  @API(description = "Get all user's disputes")
  public AccessorResponse<MdxList<Dispute>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Start a dispute
   * @param dispute
   * @return
   */
  @GatewayAPI
  @API(description = "Start dispute")
  public AccessorResponse<Dispute> start(Dispute dispute) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update dispute
   * @param dispute
   * @return
   */
  @GatewayAPI
  @API(description = "Update given dispute")
  public AccessorResponse<Dispute> update(String id, Dispute dispute) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Submit dispute
   * @return
   */
  @GatewayAPI
  @API(description = "Submit dispute")
  public AccessorResponse<Dispute> submit(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Cancel a dispute
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel dispute")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * DisputedTransaction accessor
   * @return
   */
  @API(description = "Access account's disputed transactions")
  public DisputedTransactionBaseAccessor disputedTransactions() {
    if (disputedTransactions != null) {
      return disputedTransactions;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set disputed transaction accessor
   * @param disputedTransactions
   */
  public void setDisputedTransactions(DisputedTransactionBaseAccessor disputedTransactions) {
    this.disputedTransactions = disputedTransactions;
  }
}
