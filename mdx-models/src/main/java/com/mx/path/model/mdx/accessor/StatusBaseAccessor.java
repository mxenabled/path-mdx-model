package com.mx.path.model.mdx.accessor;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.accessor.PathResponseStatus;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;

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
  public StatusBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public StatusBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get upstream system status
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Get upstream system status")
  public AccessorResponse<Void> get() {
    throw new AccessorMethodNotImplementedException();
  }
}
