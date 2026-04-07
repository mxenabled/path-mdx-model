package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.managed_card.ManagedCardAlertGateway
import com.mx.path.gateway.api.managed_card.ManagedCardGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.alerts.Alert
import com.mx.path.model.mdx.model.alerts.DeliveryMethod
import com.mx.path.model.mdx.model.managed_cards.ManagedCard

import spock.lang.Specification

class ManagedCardAlertsControllerTest extends Specification {
  ManagedCardAlertsController subject
  Gateway gateway
  ManagedCardAlertGateway alertGateway

  void setup() {
    subject = new ManagedCardAlertsController()
    alertGateway = spy(ManagedCardAlertGateway.builder().build())

    gateway = Gateway.builder()
        .managedCards(ManagedCardGateway.builder()
        .alerts(alertGateway)
        .build())
        .build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "getAlert interacts with gateway"() {
    given:
    def cardId = "card-id"
    def alertId = "alert-id"
    def alert = new Alert()

    when:
    doReturn(new AccessorResponse<Alert>().withResult(alert)).when(alertGateway).get(cardId, alertId)
    def response = subject.getAlert(cardId, alertId)

    then:
    verify(alertGateway).get(cardId, alertId) || true
    response.body == alert
  }

  def "getAlertList interacts with gateway"() {
    given:
    def cardId = "card-id"
    def alerts = new MdxList<Alert>().tap {
      add(new Alert())
    }

    when:
    doReturn(new AccessorResponse<MdxList<Alert>>().withResult(alerts)).when(alertGateway).list(cardId)
    def response = subject.getAlertList(cardId)

    then:
    verify(alertGateway).list(cardId) || true
    response.body == alerts
  }

  def "updateAlert interacts with gateway"() {
    given:
    def cardId = "card-id"
    def alert = new Alert()

    when:
    doReturn(new AccessorResponse<Alert>().withResult(alert)).when(alertGateway).update(cardId, alert)
    def response = subject.updateAlert(cardId, alert)

    then:
    verify(alertGateway).update(cardId, alert) || true
    response.body == alert
  }

  def "getDeliveryMethods interacts with gateway"() {
    given:
    def cardId = "card-id"
    def alertId = "alert-id"
    def deliveryMethods = new MdxList<DeliveryMethod>().tap {
      add(new DeliveryMethod())
    }

    when:
    doReturn(new AccessorResponse<MdxList<DeliveryMethod>>().withResult(deliveryMethods)).when(alertGateway).deliveryMethods(cardId, alertId)
    def response = subject.getDeliveryMethods(cardId, alertId)

    then:
    verify(alertGateway).deliveryMethods(cardId, alertId) || true
    response.body == deliveryMethods
  }

  def "getCards interacts with gateway"() {
    given:
    def cards = new MdxList<ManagedCard>().tap {
      add(new ManagedCard())
    }

    when:
    doReturn(new AccessorResponse<MdxList<ManagedCard>>().withResult(cards)).when(alertGateway).cards()
    def response = subject.getCards()

    then:
    verify(alertGateway).cards() || true
    response.body == cards
  }
}
