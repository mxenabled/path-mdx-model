package com.mx.path.gateway;

import lombok.experimental.SuperBuilder;

import com.mx.common.gateway.GatewayBaseClass;
import com.mx.path.model.mdx.accessor.BaseAccessor;

@GatewayBaseClass(namespace = "com.mx.path.gateway.api", target = BaseAccessor.class, className = "Gateway")
@SuperBuilder
public abstract class BaseMdxGateway extends Gateway<BaseAccessor> {
  public BaseMdxGateway() {
    super();
  }

  public BaseMdxGateway(String clientId) {
    super(clientId);
  }
}
