package com.mx.accessors;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;

/**
 * Accessor base for upstream system status
 *
 * <p>
 * Returns {@link PathResponseStatus#NO_CONTENT} if all is well.
 * </p>
 *
 * <p>
 * Returns {@link PathResponseStatus#SERVICE_UNAVAILABLE} if upstream system is unavailable
 * </p>
 *
 * <p>
 * Returns {@link PathResponseStatus#GATEWAY_TIMEOUT} if upstream system not responsive or circuit breaker is open
 * </p>
 */
@API(description = "Returns system status")
public abstract class StatusBaseAccessor extends Accessor {

  public StatusBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get upstream system status
   * @return
   */
  @GatewayAPI
  @API(description = "Get upstream system status")
  public AccessorResponse<Void> get() {
    throw new AccessorMethodNotImplementedException();
  }
}
