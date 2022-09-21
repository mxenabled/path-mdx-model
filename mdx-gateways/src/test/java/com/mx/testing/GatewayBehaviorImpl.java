package com.mx.testing;

import com.mx.common.accessors.API;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.collections.ObjectMap;
import com.mx.path.gateway.behavior.GatewayBehavior;
import com.mx.path.gateway.context.GatewayRequestContext;

@API(description = "Test behavior")
public class GatewayBehaviorImpl extends GatewayBehavior {
  public GatewayBehaviorImpl(ObjectMap configurations) {
    super(configurations);
  }

  @Override
  protected <T> AccessorResponse<T> call(Class<T> resultType, GatewayRequestContext request, GatewayBehavior terminatingBehavior) {
    return callNext(resultType, request, terminatingBehavior);
  }
}
