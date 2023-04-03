package com.mx.path.model.mdx.web

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verify

import com.mx.common.messaging.MessageBroker
import com.mx.path.api.connect.messaging.remote.RemoteService
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.configuration.AccessorProxy
import com.mx.path.gateway.configuration.Configurator
import com.mx.path.gateway.remote.remote_deposit.RemoteRemoteDepositGateway
import com.mx.path.model.context.facility.Facilities
import com.mx.path.testing.WithMockery
import com.mx.testing.MockGatewayService
import com.mx.testing.TestAccessor
import com.mx.testing.TestBehavior
import com.mx.testing.TestStore

import spock.lang.Specification
import spock.lang.Unroll

class SpringMdxGatewayManagerTest extends Specification implements WithMockery {
  def static serviceStarted
  MessageBroker messageBroker

  def setup() {
    setupMockery()
    SpringMdxGatewayManager.reset()
    serviceStarted = false
    messageBroker = mock(MessageBroker)
    Facilities.MESSAGE_BROKERS.put("afcu", messageBroker)
  }

  def cleanup() {
    cleanupMockery()
    SpringMdxGatewayManager.reset()
    Facilities.reset()
  }

  def withJson() {
    SpringMdxGatewayManager.setConfigPaths("src/test/resources/gateway.json")
  }

  def withYaml() {
    SpringMdxGatewayManager.setConfigPaths("src/test/resources/gateway.yaml")
  }

  def withCompiledYaml() {
    SpringMdxGatewayManager.setConfigPaths("src/test/resources/compiled-test/gateway.yaml")
  }

  // todo: revisit how much pre-processing should be present in SpringMdxGatewayManager
  // def withYml() {
  //   SpringMdxGatewayManager.setConfigPaths("src/test/resources/gateway.yml")
  // }

  def "throws an exception when no Gateway file is found"() {
    given:
    def subject = new SpringMdxGatewayManager()
    SpringMdxGatewayManager.setConfigPaths(new String[0])

    when:
    subject.setActiveProfiles("qa,development")

    then:
    thrown(RuntimeException)
  }

  def "setActiveProfiles json"() {
    when:
    withJson()
    makeGateway("qa,development")

    then:
    SpringMdxGatewayManager.getActiveProfiles().size() == 2
  }

  def "setActiveProfiles yaml"() {
    when:
    withYaml()
    makeGateway("qa,development")

    then:
    SpringMdxGatewayManager.getActiveProfiles().size() == 2
  }

  // def "setActiveProfiles yml"() {
  //   when:
  //   withYml()
  //   makeGateway("qa,development")
  //
  //   then:
  //   SpringMdxGatewayManager.getActiveProfiles().size() == 2
  // }

  def "getClientGateway json"() {
    when:
    withJson()
    def gateway = makeGateway("development")

    then:
    verifyClientGateway(gateway)
  }

  def "getClientGateway yaml"() {
    when:
    withYaml()
    def gateway = makeGateway("development")

    then:
    verifyClientGateway(gateway)
  }

  // def "getClientGateway yml"() {
  //   when:
  //   withYml()
  //   def gateway = makeGateway("development")
  //
  //   then:
  //   verifyClientGateway(gateway)
  // }

  def "startGatewayService and registerRemote json"() {
    assert !serviceStarted

    when:
    withJson()
    def gateway = makeGateway("development")

    then:
    verifyRemoteGateway(gateway)
    verifyStartedService(gateway)
    verifyBehavior(gateway)
    verifyAccessor(gateway)
    verifyFacilities()
    assert serviceStarted
  }

  def "startGatewayService and registerRemote yaml"() {
    assert !serviceStarted

    when:
    withYaml()
    def gateway = makeGateway("development")

    then:
    verifyRemoteGateway(gateway)
    verifyStartedService(gateway)
    verifyBehavior(gateway)
    verifyAccessor(gateway)
    verifyFacilities()
    assert serviceStarted
  }

  // def "startGatewayService and registerRemote yml"() {
  //   assert !serviceStarted
  //
  //   when:
  //   withYml()
  //   def gateway = makeGateway("development")
  //
  //   then:
  //   verifyRemoteGateway(gateway)
  //   verifyStartedService(gateway)
  //   verifyBehavior(gateway)
  //   verifyAccessor(gateway)
  //   verifyFacilities()
  //   assert serviceStarted
  // }

  @Unroll
  def "compiled configs for #baseConfig yaml"() {
    assert !serviceStarted

    when:
    withCompiledYaml()
    def partialConfig = "k8s"
    def gateway = makeGateway(baseConfig + "," + partialConfig)

    then:
    verifyRemoteGateway(gateway)
    verifyStartedService(gateway)
    verifyBehavior(gateway)
    verifyAccessor(gateway)
    verifyFacilities()
    assert serviceStarted

    where:
    baseConfig << [
      "development",
      "int",
      "qa",
      "sand"
    ]
  }

  def "executes after initialized hooks"() {
    when:
    def called = false
    SpringMdxGatewayManager.registerAfterInitialized({ Configurator<Gateway> configurator, Map<String, Gateway> gateways ->
      called = true
    })
    withYaml()
    def gateway = makeGateway("development")

    then:
    called

    when:
    SpringMdxGatewayManager.registerAfterInitialized({ Configurator<Gateway> configurator, Map<String, Gateway> gateways ->
      // not allowed
    })

    then: "reset"
    def ex = thrown(MdxWebApplicationException)
    ex.getMessage() == "Initialization order issue. Calling registerAfterInitialized after gateways already initialized."

    when: "registration is allowed after reset"
    SpringMdxGatewayManager.reset()

    SpringMdxGatewayManager.registerAfterInitialized({ Configurator<Gateway> configurator, Map<String, Gateway> gateways ->
      called = true
    })

    called = false
    withYaml()
    gateway = makeGateway("development")

    then:
    called
  }

  def "executes before initialization hook"() {
    when:
    def called = false
    SpringMdxGatewayManager.registerBeforeInitialization({ Configurator<Gateway> configurator ->
      called = true
    })
    withYaml()
    makeGateway("development")

    then:
    called
  }

  def makeGateway(profiles, clientId = "afcu") {
    new SpringMdxGatewayManager().setActiveProfiles(profiles)
    return SpringMdxGatewayManager.getClientGateway(clientId)
  }

  def verifyRemoteGateway(gateway) {
    assert verify(messageBroker).registerResponder(eq("path.request.afcu.RemoteDepositBaseAccessor.get"), any(RemoteService)) || true
    gateway.remoteDeposits().getRemote().class == RemoteRemoteDepositGateway
  }

  def verifyStartedService(gateway) {
    assert gateway.getServices().size() == 0
    assert gateway.remoteDeposits().getServices().size() == 1
    def gatewayService = gateway.remoteDeposits().getServices().get(0)
    gatewayService instanceof MockGatewayService
  }

  def verifyAccessor(gateway) {
    def accessor = (AccessorProxy) gateway.getAccessor()
    accessor.build() instanceof TestAccessor
  }

  def verifyBehavior(gateway) {
    assert gateway.remoteDeposits().getBehaviors().size() == 1
    def gatewayBehavior = gateway.remoteDeposits().getBehaviors().get(0)
    gatewayBehavior instanceof TestBehavior
  }

  def verifyClientGateway(gateway) {
    gateway.remoteDeposits().getAccessor().getConfiguration().getConfigurations().get("test") == "devProfiles"
  }

  def verifyFacilities(clientId = "afcu") {
    def cacheStore = Facilities.getCacheStore(clientId)
    assert cacheStore instanceof TestStore
    assert cacheStore.configurations.getAsString("host") == "localhost"
    assert cacheStore.configurations.getAsInteger("port") == 6379
    assert cacheStore.configurations.getAsInteger("connectionTimeoutSeconds") == 10
    assert cacheStore.configurations.getAsInteger("computationThreadPoolSize") == 5
    assert cacheStore.configurations.getAsInteger("ioThreadPoolSize") == 5

    def sessionStore = Facilities.getSessionStore(clientId)
    assert sessionStore instanceof TestStore
    assert sessionStore.configurations.getAsString("host") == "localhost"
    assert sessionStore.configurations.getAsInteger("port") == 6380
    assert sessionStore.configurations.getAsInteger("connectionTimeoutSeconds") == 8
    assert sessionStore.configurations.getAsInteger("computationThreadPoolSize") == 7
    assert sessionStore.configurations.getAsInteger("ioThreadPoolSize") == 6

    return true
  }
}
