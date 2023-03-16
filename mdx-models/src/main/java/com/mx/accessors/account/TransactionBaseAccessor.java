package com.mx.accessors.account;

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
import com.mx.models.account.Transaction;
import com.mx.models.account.TransactionSearchRequest;
import com.mx.models.account.TransactionsPage;
import com.mx.models.account.options.TransactionListOptions;

/**
 * Accessor for id operations
 */
@GatewayClass
@API(description = "Access to account transactions", specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#transactions")
public abstract class TransactionBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private CheckImageBaseAccessor checkImages;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private DisputeBaseAccessor disputes;

  public TransactionBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Accessor for check image operations
   * @return accessor
   */
  @API
  public CheckImageBaseAccessor checkImages() {
    if (checkImages != null) {
      return checkImages;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for dispute operations
   * @return accessor
   */
  public DisputeBaseAccessor disputes() {
    if (disputes != null) {
      return disputes;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set accessor for check images
   * @param checkImagesBaseAccessor
   */
  public void setCheckImages(CheckImageBaseAccessor checkImagesBaseAccessor) {
    this.checkImages = checkImagesBaseAccessor;
  }

  /**
   * Set accessor for disputes
   * @param disputesBaseAccessor
   */
  public void setDisputes(DisputeBaseAccessor disputesBaseAccessor) {
    this.disputes = disputesBaseAccessor;
  }

  /**
   * Get all transactions for this account
   * @return
   */
  @GatewayAPI
  @API(description = "List recent transactions")
  public AccessorResponse<MdxList<Transaction>> recent(String accountId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List transactions
   * @param accountId
   * @param transactionListOptions
   * @return
   */
  @GatewayAPI
  @API(description = "List transactions")
  public AccessorResponse<MdxList<Transaction>> list(String accountId, TransactionListOptions transactionListOptions) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Pending transactions
   * @param accountId
   * @return
   */
  @GatewayAPI
  @API(description = "List pending transactions")
  public AccessorResponse<MdxList<Transaction>> pending(String accountId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Search paged transactions
   * @param searchRequest
   * @return
   */
  @GatewayAPI
  @API(description = "Find transactions matching SearchRequest")
  public AccessorResponse<TransactionsPage> search(String accountId, TransactionSearchRequest searchRequest) {
    throw new AccessorMethodNotImplementedException();
  }

}
