package com.mx.testing

import com.mx.common.collections.ObjectMap
import com.mx.path.gateway.service.GatewayService
import com.mx.path.model.mdx.web.SpringMdxGatewayManagerTest

public class MockGatewayService extends GatewayService {

  MockGatewayService(ObjectMap configurations) {
    super(configurations)
  }

  @Override
  void start() {
    SpringMdxGatewayManagerTest.serviceStarted = true
  }

  @Override
  void stop() {
  }
}
