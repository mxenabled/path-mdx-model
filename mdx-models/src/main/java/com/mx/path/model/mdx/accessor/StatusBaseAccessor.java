package com.mx.path.model.mdx.accessor;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.accessors.PathResponseStatus;
import com.mx.common.gateway.GatewayAPI;

/**
 * Accessor base for upstream system status
 *
 * <p>Returns {@link PathResponseStatus#NO_CONTENT} if all is well.
 *
 * <p>Returns {@link PathResponseStatus#UNAVAILABLE} if upstream system is unavailable
 *
 * <p>Returns {@link PathResponseStatus#TIMEOUT} if upstream system not responsive or circuit breaker is open
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
