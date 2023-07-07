package com.mx.path.model.mdx.accessor.account;

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

  public DisputeBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public DisputeBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a dispute by id
   *
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
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Get all user's disputes")
  public AccessorResponse<MdxList<Dispute>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Start a dispute
   *
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
   *
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
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Submit dispute")
  public AccessorResponse<Dispute> submit(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Cancel a dispute
   *
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
   *
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
   *
   * @param disputedTransactions
   */
  public void setDisputedTransactions(DisputedTransactionBaseAccessor disputedTransactions) {
    this.disputedTransactions = disputedTransactions;
  }
}
