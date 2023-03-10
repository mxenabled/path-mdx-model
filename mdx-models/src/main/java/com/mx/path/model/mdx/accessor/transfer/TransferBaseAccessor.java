package com.mx.path.model.mdx.accessor.transfer;

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
import com.mx.path.model.mdx.accessor.transfer.recurring_transfer.RecurringTransferBaseAccessor;
import com.mx.path.model.mdx.model.transfer.Transfer;
import com.mx.path.model.mdx.model.transfer.options.TransferListOptions;

/**
 * Accessor base for transfer operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/transfer/#mdx-transfer">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/transfer/#mdx-transfer")
public abstract class TransferBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AccountBaseAccessor accounts;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RecurringTransferBaseAccessor recurring;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AmountOptionBaseAccessor transferAmountOptions;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private FeeBaseAccessor fees;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RepaymentBaseAccessor repayments;

  public TransferBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a transfer
   * @param transfer
   * @return
   */
  @GatewayAPI
  @API(description = "Create a transfer")
  public AccessorResponse<Transfer> create(Transfer transfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a transfer")
  public AccessorResponse<Transfer> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all transfers
   * @return
   */
  @GatewayAPI
  @API(description = "List all transfers")
  public AccessorResponse<MdxList<Transfer>> list(TransferListOptions options) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a transfer
   *
   * <p>Note: Typically only available on future-dated transfers or transfers that haven't posted
   * @param transfer
   * @return
   */
  @GatewayAPI
  @API(description = "Update a transfer", notes = "Typically only available on future-dated transfers or transfers that haven't posted")
  public AccessorResponse<Transfer> update(String id, Transfer transfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Cancel a transfer
   *
   * <p>Note: Typically only available on future-dated transfers or transfers that haven't posted
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel a transfer", notes = "Typically only available on future-dated transfers or transfers that haven't posted")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Start a multistage transfer
   * @param transfer
   * @return
   */
  @GatewayAPI
  @API(description = "Start a multistage transfer")
  public AccessorResponse<Transfer> start(Transfer transfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Finish a multistage transfer
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Finish a multistage transfer")
  public AccessorResponse<Transfer> finish(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for transfer account operations
   * @return accessor
   */
  @API(description = "Access transfer accounts")
  public AccountBaseAccessor accounts() {
    if (accounts != null) {
      return accounts;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set transfer account accessor
   * @param accounts
   */
  public void setAccounts(AccountBaseAccessor accounts) {
    this.accounts = accounts;
  }

  /**
   * Accessor for recurring transfer operations
   * @return accessor
   */
  @API(description = "Access recurring transfers")
  public RecurringTransferBaseAccessor recurring() {
    if (recurring != null) {
      return recurring;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set recurring transfer accessor
   * @param recurring
   */
  public void setRecurring(RecurringTransferBaseAccessor recurring) {
    this.recurring = recurring;
  }

  /**
   * Accessor for transfer amount option operations
   * @return accessor
   */
  @API(description = "Access transfer amount options")
  public AmountOptionBaseAccessor transferAmountOptions() {
    if (transferAmountOptions != null) {
      return transferAmountOptions;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set transfer amount option accessor
   * @param transferAmountOptions
   */
  public void setTransferAmountOptions(AmountOptionBaseAccessor transferAmountOptions) {
    this.transferAmountOptions = transferAmountOptions;
  }

  /**
   * Accessor for transfer fee operations
   * @return accessor
   */
  @API(description = "Access transfer fees")
  public FeeBaseAccessor fees() {
    if (fees != null) {
      return fees;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set transfer fee accessor
   * @param fees
   */
  public void setFees(FeeBaseAccessor fees) {
    this.fees = fees;
  }

  /**
   * Accessor for transfer repayment operations
   * @return accessor
   */
  @API(description = "Access transfer repayments")
  public RepaymentBaseAccessor repayments() {
    if (repayments != null) {
      return repayments;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set transfer repayment accessor
   * @param repayments
   */
  public void setRepayments(RepaymentBaseAccessor repayments) {
    this.repayments = repayments;
  }
}
