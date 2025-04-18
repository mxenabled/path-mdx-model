package com.mx.path.gateway

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verify

import com.mx.path.core.common.collection.ObjectMap
import com.mx.path.gateway.accessor.AccessorConfiguration
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.id.IdGateway
import com.mx.path.gateway.behavior.BlockBehavior
import com.mx.path.gateway.behavior.GatewayBehavior
import com.mx.path.gateway.context.GatewayRequestContext
import com.mx.path.gateway.remote.account.RemoteAccountGateway
import com.mx.path.gateway.service.GatewayService
import com.mx.path.model.mdx.accessor.BaseAccessor
import com.mx.path.model.mdx.model.account.Account
import com.mx.testing.BaseMdxGatewayImpl

import spock.lang.Specification

class BaseMdxGatewayTest extends Specification {
  BaseAccessor accessor
  GatewayBehavior behavior

  def setup() {
    accessor = new AccessorImpl(AccessorConfiguration.builder().clientId("mx").build())
    behavior = new GatewayBehaviorImpl()
  }

  class GatewayBehaviorImpl extends GatewayBehavior {
    GatewayBehaviorImpl() {
      super(new ObjectMap())
    }

    @Override
    protected <T> AccessorResponse<T> call(Class<T> resultType, GatewayRequestContext request, GatewayBehavior terminatingBehavior) {
      return callNext(resultType, request, terminatingBehavior)
    }
  }

  class AccessorImpl extends BaseAccessor {
    AccessorImpl(AccessorConfiguration configuration) {
      super(configuration)
    }
  }

  def "builder"() {
    when:
    def subject = BaseMdxGatewayImpl.builder()
        .baseAccessor(accessor)
        .behavior(behavior)
        .build()

    then:
    subject.getBaseAccessor() == accessor
    subject.behaviors.get(0) == behavior
  }

  def "executeStack"() {
    given:
    def subject = BaseMdxGatewayImpl.builder()
        .baseAccessor(accessor)
        .behavior(new GatewayBehaviorImpl())
        .build()

    def executed = false
    def terminatingBehavior = new BlockBehavior({ request ->
      executed = true
      return new AccessorResponse<Account>()
    })

    def gatewayRequestContext = GatewayRequestContext.builder().build()

    when:
    def result = subject.executeBehaviorStack(Account.class, gatewayRequestContext, terminatingBehavior)

    then:
    result != null
    executed
  }

  def "startServices has sets gateway and starts"() {
    given:
    def service = mock(GatewayService)
    def subject = BaseMdxGatewayImpl.builder()
        .service(service)
        .build()

    when:
    subject.startServices()

    then:
    verify(service).setGateway(subject)
    verify(service).start()
  }

  def "registerRemotes"() {
    given:
    def remote = mock(RemoteAccountGateway)
    def subject = BaseMdxGatewayImpl.builder()
        .remote(remote)
        .build()

    when:
    subject.registerRemotes()

    then:
    verify(remote).register() || true
  }

  def "gateways"() {
    given:
    def subject = BaseMdxGatewayImpl.builder().build()

    when:
    def accounts = new AccountGateway()
    subject.setAccounts (accounts)

    then:
    subject.gateways().size() == 1
    subject.gateways().contains(accounts)

    when:
    def id = new IdGateway()
    subject.setId(id)

    then:
    subject.gateways().size() == 2
    subject.gateways().contains(id)
  }

  def "behaviors are immutable"() {
    given:
    def subject = BaseMdxGatewayImpl.builder().build()

    when:
    subject.behaviors.add(null)

    then:
    thrown(UnsupportedOperationException)
  }

  def "services are immutable"() {
    given:
    def subject = BaseMdxGatewayImpl.builder().build()
    def behavior = new GatewayBehaviorImpl()

    when:
    subject.services.add(null)

    then:
    thrown(UnsupportedOperationException)
  }
}
