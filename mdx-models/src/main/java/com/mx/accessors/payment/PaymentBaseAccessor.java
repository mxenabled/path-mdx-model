package com.mx.accessors.payment;

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
import com.mx.models.account.Account;
import com.mx.models.payment.Payment;

/**
 * Accessor base for payment operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/payment/#payments">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment/#payments")
public abstract class PaymentBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private BillBaseAccessor bills;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private MerchantBaseAccessor merchants;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private PayeeBaseAccessor payees;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RecurringPaymentBaseAccessor recurring;

  public PaymentBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List accounts to pay from
   * @return
   */
  @GatewayAPI
  @API(description = "Account options to fund payments")
  public AccessorResponse<MdxList<Account>> accounts() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create new payment
   * @param payment
   * @return
   */
  @GatewayAPI
  @API(description = "Create new payment")
  public AccessorResponse<Payment> create(Payment payment) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a payment
   * @param payment
   * @return
   */
  @GatewayAPI
  @API(description = "Update a payment")
  public AccessorResponse<Payment> update(String id, Payment payment) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Cancel a payment
   *
   * <p>Note: Typically applicable to future-dated payments or payments that are not posted
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel a payment", notes = "Typically applicable to future-dated payments or payments that are not posted")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a payment
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a payment")
  public AccessorResponse<Payment> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all payments
   * @return
   */
  @GatewayAPI
  @API(description = "List all payments")
  public AccessorResponse<MdxList<Payment>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for bill operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment/#bills")
  public BillBaseAccessor bills() {
    if (bills != null) {
      return bills;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set bills accessor
   * @param bills
   */
  public void setBills(BillBaseAccessor bills) {
    this.bills = bills;
  }

  /**
   * Accessor for merchant operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment/#merchants")
  public MerchantBaseAccessor merchants() {
    if (merchants != null) {
      return merchants;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set merchants accessor
   * @param merchants
   */
  public void setMerchants(MerchantBaseAccessor merchants) {
    this.merchants = merchants;
  }

  /**
   * Accessor for merchant operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment/#payees")
  public PayeeBaseAccessor payees() {
    if (payees != null) {
      return payees;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set payees accessor
   * @param payees
   */
  public void setPayees(PayeeBaseAccessor payees) {
    this.payees = payees;
  }

  /**
   * Accessor for recurring payment operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/payment#recurring-payments")
  public RecurringPaymentBaseAccessor recurring() {
    if (recurring != null) {
      return recurring;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set recurring payment accessor
   * @param recurring
   */
  public void setRecurring(RecurringPaymentBaseAccessor recurring) {
    this.recurring = recurring;
  }
}
