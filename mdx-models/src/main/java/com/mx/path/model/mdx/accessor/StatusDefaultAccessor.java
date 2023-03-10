package com.mx.path.model.mdx.accessor;

import com.mx.common.accessors.API;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.accessors.PathResponseStatus;

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
