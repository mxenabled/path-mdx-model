package com.mx.path.model.mdx.accessor.p2p_transfer;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;

/**
 * Accessor base for p2p transfer operations
 *
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#p2p-transfers")
public class P2PTransferBaseAccessor extends Accessor {
  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AccountBaseAccessor accounts;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private DurationBaseAccessor durations;

  /**
   * Accessor for account operations
   *
   * @return accessor
   */
  @API
  public AccountBaseAccessor accounts() {
    return accounts;
  }

  /**
   * Sets account accessor
   * @param accounts
   */
  public void setAccounts(AccountBaseAccessor accounts) {
    this.accounts = accounts;
  }

  /**
   * Accessor for duration operations
   *
   * @return accessor
   */
  @API
  public DurationBaseAccessor durations() {
    return durations;
  }

  /**
   * Sets duration accessor
   * @param durations
   */
  public void setDurations(DurationBaseAccessor durations) {
    this.durations = durations;
  }
}
