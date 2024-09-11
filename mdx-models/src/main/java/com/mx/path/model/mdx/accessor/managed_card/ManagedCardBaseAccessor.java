package com.mx.path.model.mdx.accessor.managed_card;

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
import com.mx.path.model.mdx.model.managed_cards.ManagedCard;

/**
 * Accessor base for managed card operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/managed_cards/#mdx-managed-cards">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/managed_cards/#mdx-managed-cards")
public abstract class ManagedCardBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private MerchantBaseAccessor merchants;

  public ManagedCardBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public ManagedCardBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Activate a managed card
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Activate managed card")
  public AccessorResponse<ManagedCard> activate(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create a managed card
   *
   * @param card
   * @return
   */
  @GatewayAPI
  @API(description = "Create managed card")
  public AccessorResponse<ManagedCard> create(ManagedCard card) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Enable a managed card
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Enable managed card")
  public AccessorResponse<Void> enable(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a managed card
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get managed card")
  public AccessorResponse<ManagedCard> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get unmasked card number
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get unmasked card number")
  public AccessorResponse<ManagedCard> getUnmaskedCardNumber(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get cvv
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get unmasked CVV")
  public AccessorResponse<ManagedCard> getCvv(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List managed cards
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List managed cards")
  public AccessorResponse<MdxList<ManagedCard>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Pause a managed card
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Pause managed card")
  public AccessorResponse<Void> pause(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Replace a managed card
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Replace a managed card")
  public AccessorResponse<ManagedCard> replace(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set managed card pin
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Set managed card pin")
  public AccessorResponse<ManagedCard> setPin(String id, ManagedCard card) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a managed card
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Update a managed card")
  public AccessorResponse<ManagedCard> update(String id, ManagedCard card) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for merchant operations
   *
   * @return accessor
   */
  @API
  public MerchantBaseAccessor merchants() {
    return merchants;
  }

  /**
   * Sets merchants accessor
   * @param merchants
   */
  public void setMerchants(MerchantBaseAccessor merchants) {
    this.merchants = merchants;
  }
}
