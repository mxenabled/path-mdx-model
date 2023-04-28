package com.mx.path.gateway

import static org.mockito.Mockito.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.never
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import com.mx.path.core.context.facility.Facilities
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.context.GatewayRequestContext
import com.mx.path.gateway.event.AfterAccessorEvent
import com.mx.path.gateway.event.BeforeAccessorEvent
import com.mx.path.gateway.event.GatewayEventBus
import com.mx.path.model.mdx.accessor.BaseAccessor
import com.mx.path.model.mdx.accessor.account.AccountBaseAccessor

import spock.lang.Specification

class GatewayEventTest extends Specification {

  AccountGateway subject
  GatewayEventBus eventBus
  BaseAccessor accessor
  AccountBaseAccessor accountAccessor
  String clientId = "client1"

  def setup() {
    eventBus = mock(GatewayEventBus)
    Facilities.addEventBus(clientId, eventBus)

    accessor = mock(BaseAccessor)
    accountAccessor = mock(AccountBaseAccessor)
    when(accessor.accounts()).thenReturn(accountAccessor)
    subject = AccountGateway.builder().baseAccessor(accessor).build()
  }

  def cleanup() {
    Facilities.reset()
  }

  def "emits accessor events"() {
    given:
    GatewayRequestContext.builder().currentAccessor(accountAccessor).clientId(clientId).feature("accounts").build().register()

    when:
    subject.list()

    then:
    verify(eventBus).post(any(BeforeAccessorEvent.class)) || true
    verify(eventBus).post(any(AfterAccessorEvent.class)) || true
  }

  def "does not emit when no subscribers"() {
    given:
    GatewayRequestContext.builder().currentAccessor(accountAccessor).clientId("someOtherGuy").feature("accounts").build().register()

    when:
    subject.list()

    then:
    verify(eventBus, never()).post(any(BeforeAccessorEvent.class)) || true
    verify(eventBus, never()).post(any(AfterAccessorEvent.class)) || true
  }
}
