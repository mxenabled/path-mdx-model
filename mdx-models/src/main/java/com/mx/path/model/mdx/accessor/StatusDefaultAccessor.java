package com.mx.path.model.mdx.accessor;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.PathResponseStatus;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;

@API(notes = "Default status operations.")
public class StatusDefaultAccessor extends StatusBaseAccessor {
  public StatusDefaultAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  @API(notes = "Does no health check, just returns 204")
  public final AccessorResponse<Void> get() {
    return new AccessorResponse<Void>().withStatus(PathResponseStatus.NO_CONTENT);
  }
}
