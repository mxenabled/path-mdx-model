package com.mx.testing;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.collection.ObjectMap;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.gateway.behavior.GatewayBehavior;
import com.mx.path.gateway.context.GatewayRequestContext;

@API(description = "Test Exception behavior")
public class MDXGatewayBehaviorImpl extends GatewayBehavior {
  public MDXGatewayBehaviorImpl(ObjectMap configurations) {
    super(configurations);
  }

  @Override
  protected <T> AccessorResponse<T> call(Class<T> resultType, GatewayRequestContext request, GatewayBehavior terminatingBehavior) {
    return callNext(resultType, request, terminatingBehavior);
  }
}
