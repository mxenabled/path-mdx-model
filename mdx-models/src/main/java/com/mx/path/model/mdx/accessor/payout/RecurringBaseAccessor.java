package com.mx.path.model.mdx.accessor.payout;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.payout.RecurringPayout;

/**
 * Accessor for recurring payout method operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/payout/#recurring-payouts")
public class RecurringBaseAccessor extends Accessor {
  public RecurringBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public RecurringBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List all recurring payout
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all recurring payout")
  public AccessorResponse<MdxList<RecurringPayout>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create a recurring payout
   *
   * @param recurringPayout
   * @return
   */
  @GatewayAPI
  @API(description = "Create a recurring payout")
  public AccessorResponse<RecurringPayout> create(RecurringPayout recurringPayout) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a recurring payout
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recurring payout")
  public AccessorResponse<RecurringPayout> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a recurring payout
   *
   * @param id
   * @param recurringPayout
   * @return
   */
  @GatewayAPI
  @API(description = "Update a recurring payout")
  public AccessorResponse<RecurringPayout> update(String id, RecurringPayout recurringPayout) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Cancel a recurring payout
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Cancel a recurring payout")
  public AccessorResponse<Void> cancel(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List the available frequencies for recurring payouts
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all recurring payout")
  public AccessorResponse<MdxList<Frequency>> frequencies() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Skip next occurrence of recurring payout
   *
   * @param id
   * todo: Add recurring payout to response (to sync this up with recurring transfers)
   * @return
   */
  @GatewayAPI
  @API(description = "Skip next occurrence of recurring payout")
  public AccessorResponse<RecurringPayout> skipNext(String id) {
    throw new AccessorMethodNotImplementedException();
  }

}
