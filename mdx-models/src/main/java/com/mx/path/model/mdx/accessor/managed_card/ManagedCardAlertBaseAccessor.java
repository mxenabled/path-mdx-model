package com.mx.path.model.mdx.accessor.managed_card;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.alerts.Alert;
import com.mx.path.model.mdx.model.alerts.DeliveryMethod;
import com.mx.path.model.mdx.model.managed_cards.ManagedCard;

/**
 * Accessor for managed card alert operations
 */
@GatewayClass
@API(description = "Access to managed card alerts", specificationUrl = "https://developer.mx.com/drafts/mdx/managed_cards/#alerts")
public abstract class ManagedCardAlertBaseAccessor extends Accessor {
  public ManagedCardAlertBaseAccessor() {
  }

  /**
   * Get a managed card alert
   *
   * @param cardId
   * @param alertId
   * @return
   */
  @GatewayAPI
  @API(description = "Get managed card alert")
  public AccessorResponse<Alert> get(String cardId, String alertId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List managed card alerts
   *
   * @param cardId
   * @return
   */
  @GatewayAPI
  @API(description = "List managed card alerts")
  public AccessorResponse<MdxList<Alert>> list(String cardId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a managed card alert
   *
   * @param cardId
   * @param alert
   * @return
   */
  @GatewayAPI
  @API(description = "Update a managed card alert")
  public AccessorResponse<Alert> update(String cardId, Alert alert) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List delivery methods
   *
   * @param cardId
   * @param alertId
   * @return
   */
  @GatewayAPI
  @API(description = "List delivery methods")
  public AccessorResponse<MdxList<DeliveryMethod>> deliveryMethods(String cardId, String alertId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List managed cards eligible for alerts
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List managed cards eligible for alerts")
  public AccessorResponse<MdxList<ManagedCard>> cards() {
    throw new AccessorMethodNotImplementedException();
  }
}
