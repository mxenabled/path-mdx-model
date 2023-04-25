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
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;

/**
 * Accessor for account operations
 */
@GatewayClass
@API(description = "Access to user accounts", specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#mdx-account")
public abstract class AccountBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AccountNumberBaseAccessor accountNumbers;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AccountOwnerBaseAccessor accountOwners;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private TransactionBaseAccessor transactions;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RepaymentBaseAccessor repayments;

  public AccountBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Account number accessor
   * @return
   */
  @API(description = "Access account numbers")
  public AccountNumberBaseAccessor accountNumbers() {
    if (accountNumbers != null) {
      return accountNumbers;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Account owner accessor
   * @return
   */
  @API(description = "Access account owners")
  public AccountOwnerBaseAccessor accountOwners() {
    if (accountOwners != null) {
      return accountOwners;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Transaction accessor
   * @return
   */
  @API(description = "Access account's transactions")
  public TransactionBaseAccessor transactions() {
    if (transactions != null) {
      return transactions;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Repayment accessor
   * @return
   */
  @API(description = "Access account's repayments")
  public RepaymentBaseAccessor repayments() {
    if (repayments != null) {
      return repayments;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set account number accessor
   * @param accountNumbers
   */
  public void setAccountNumbers(AccountNumberBaseAccessor accountNumbers) {
    this.accountNumbers = accountNumbers;
  }

  /**
   * Set account owner accessor
   * @param accountOwners
   */
  public void setAccountOwners(AccountOwnerBaseAccessor accountOwners) {
    this.accountOwners = accountOwners;
  }

  /**
   * Set transaction accessor
   * @param transactions
   */
  public void setTransactions(TransactionBaseAccessor transactions) {
    this.transactions = transactions;
  }

  /**
   * Set repayment accessor
   * @param repayments
   */
  public void setRepayments(RepaymentBaseAccessor repayments) {
    this.repayments = repayments;
  }

  /**
   * Get all accounts
   * @return
   */
  @GatewayAPI
  @API(description = "Get all user's account")
  public AccessorResponse<MdxList<Account>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create an account
   * @param account
   * @return
   */
  @GatewayAPI
  @API(description = "Create account")
  public AccessorResponse<Account> create(Account account) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete an account
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete account")
  public AccessorResponse<Account> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get an account by id
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get an account by id")
  public AccessorResponse<Account> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update account
   * @param account
   * @return
   */
  @GatewayAPI
  @API(description = "Update given account")
  public AccessorResponse<Account> update(String id, Account account) {
    throw new AccessorMethodNotImplementedException();
  }
}
