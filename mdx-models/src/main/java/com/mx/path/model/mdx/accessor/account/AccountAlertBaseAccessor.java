package com.mx.path.model.mdx.accessor.account;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.account.alerts.AccountAlert;
import com.mx.path.model.mdx.model.account.alerts.DeliveryMethod;

/**
 * Accessor for account alert operations
 */
@GatewayClass
@API(description = "Access to account alerts", specificationUrl = "https://developer.mx.com/drafts/mdx/accounts/#accounts-alerts")
public abstract class AccountAlertBaseAccessor extends Accessor {

  public AccountAlertBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public AccountAlertBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get an account alert
   *
   * @param accountId
   * @param alertId
   * @return
   */
  @GatewayAPI
  @API(description = "Get account alert")
  public AccessorResponse<AccountAlert> get(String accountId, String alertId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List account alerts
   *
   * @param accountId
   * @return
   */
  @GatewayAPI
  @API(description = "List account alerts")
  public AccessorResponse<MdxList<AccountAlert>> list(String accountId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update an account alert
   *
   * @param accountId
   * @param accountAlert
   * @return
   */
  @GatewayAPI
  @API(description = "Update an account alert")
  public AccessorResponse<AccountAlert> update(String accountId, AccountAlert accountAlert) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List delivery methods
   *
   * @param accountId
   * @param alertId
   * @return
   */
  @GatewayAPI
  @API(description = "List delivery methods")
  public AccessorResponse<MdxList<DeliveryMethod>> deliveryMethods(String accountId, String alertId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List accounts eligible for alerts
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List accounts eligible for alerts")
  public AccessorResponse<MdxList<Account>> accounts() {
    throw new AccessorMethodNotImplementedException();
  }
}
