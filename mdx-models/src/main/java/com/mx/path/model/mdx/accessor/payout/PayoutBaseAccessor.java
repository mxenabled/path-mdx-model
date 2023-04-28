package com.mx.path.model.mdx.accessor.payout;

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
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.payout.Payout;

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
