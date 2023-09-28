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
import com.mx.path.model.mdx.model.account.StopPayment;

/**
 * Accessor base for account stop payments operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/index.html#accounts-stop-payment")
public abstract class AccountStopPaymentsBaseAccessor extends Accessor {
  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AccountStopPaymentReasonsBaseAccessor stopPaymentReasons;

  public AccountStopPaymentsBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AccountStopPaymentsBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Stop payment reasons accessor
   * @return
   */
  @API(description = "Access stop payment reasons")
  public AccountStopPaymentReasonsBaseAccessor stopPaymentReasons() {
    if (stopPaymentReasons != null) {
      return stopPaymentReasons;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set account stop payment reasons accessor
   *
   * @param stopPaymentReasons
   */
  public void setStopPaymentReasons(AccountStopPaymentReasonsBaseAccessor stopPaymentReasons) {
    this.stopPaymentReasons = stopPaymentReasons;
  }

  /**
   * Creates a stop payment for an account
   * @param accountId
   * @param stopPayment
   * @return
   */
  @GatewayAPI
  @API(description = "Creates a stop payment for an account")
  public AccessorResponse<StopPayment> create(String accountId, StopPayment stopPayment) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all stop payments for an account
   * @param accountId
   * @return
   */
  @GatewayAPI
  @API(description = "List all stop payments for an account")
  public AccessorResponse<MdxList<StopPayment>> list(String accountId) {
    throw new AccessorMethodNotImplementedException();
  }
}
