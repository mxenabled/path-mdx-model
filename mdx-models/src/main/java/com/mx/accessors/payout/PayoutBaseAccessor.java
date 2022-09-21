package com.mx.accessors.payout;

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
import com.mx.models.payout.Payout;

/**
 * Accessor base for payout operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/payout/#mdx-payout">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payout/#mdx-payout")
public abstract class PayoutBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RecipientBaseAccessor recipients;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private RecurringBaseAccessor recurring;

  public PayoutBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List of account options for funding source
   * @return
   */
  @GatewayAPI
  @API(description = "List of account options for funding source")
  public AccessorResponse<MdxList<Account>> accounts() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create a payout
   * @param payout
   * @return
   */
  @GatewayAPI
  @API(description = "Create a payout")
  public AccessorResponse<Payout> create(Payout payout) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Cancel a payout
   *
   * <p>Typically used to cancel a future-dated payout or payout that has not posted
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel a payout", notes = "Typically used to cancel a future-dated payout or payout that has not posted")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all payouts
   * @return
   */
  @GatewayAPI
  @API(description = "List all payout")
  public AccessorResponse<MdxList<Payout>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a payout
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a payout")
  public AccessorResponse<Payout> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Enroll user in payouts
   * @return
   */
  @GatewayAPI
  @API(description = "Enroll user in payouts")
  public AccessorResponse<Void> enroll() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for recipients operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/payout/#recipients")
  public RecipientBaseAccessor recipients() {
    if (recipients != null) {
      return recipients;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set recipients accessor
   * @param recipients
   */
  public void setRecipients(RecipientBaseAccessor recipients) {
    this.recipients = recipients;
  }

  /**
   * Accessor for recurring payout accessor
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/payout/#recurring-payouts")
  public RecurringBaseAccessor recurring() {
    if (recurring != null) {
      return recurring;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set recurring payout accessor
   * @param recurring
   */
  public void setRecurring(RecurringBaseAccessor recurring) {
    this.recurring = recurring;
  }

}
