package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.managed_card.ManagedCardGateway
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.managed_cards.ManagedCard

import org.springframework.http.HttpStatus

import spock.lang.Specification

class ManagedCardsControllerTest extends Specification {
  ManagedCardsController subject
  Gateway gateway
  ManagedCardGateway managedCardGateway

  def setup() {
    subject = new ManagedCardsController()

    managedCardGateway = spy(ManagedCardGateway.builder().build())
    gateway = spy(Gateway.builder().managedCards(managedCardGateway).build())

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "get interacts with gateway"() {
    given:
    MdxList<ManagedCard> managedCards = new MdxList<>().tap {
      add(new ManagedCard())
    }

    when:
    doReturn(new AccessorResponse<MdxList<ManagedCard>>().withResult(managedCards)).when(managedCardGateway).list()
    def response = subject.list()

    then:
    verify(managedCardGateway).list() || true
    response.body == managedCards
  }

  def "index interacts with gateway"() {
    given:
    ManagedCard managedCard = new ManagedCard()

    when:
    doReturn(new AccessorResponse<ManagedCard>().withResult(managedCard)).when(managedCardGateway).get("mc-123")
    def response = subject.get("mc-123")

    then:
    verify(managedCardGateway).get("mc-123") || true
    response.body == managedCard
  }

  def "enable interacts with gateway"() {
    given:
    ManagedCard managedCard = new ManagedCard()

    when:
    doReturn(new AccessorResponse<ManagedCard>().withResult(managedCard)).when(managedCardGateway).enable("mc-123")
    def response = subject.enable("mc-123")

    then:
    verify(managedCardGateway).enable("mc-123") || true
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "disable interacts with gateway"() {
    given:
    ManagedCard managedCard = new ManagedCard()

    when:
    doReturn(new AccessorResponse<ManagedCard>().withResult(managedCard)).when(managedCardGateway).pause("mc-123")
    def response = subject.pause("mc-123")

    then:
    verify(managedCardGateway).pause("mc-123") || true
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "create interacts with gateway"() {
    given:
    ManagedCard managedCard = new ManagedCard()
    doReturn(new AccessorResponse<ManagedCard>().withResult(managedCard)).when(managedCardGateway).create(managedCard)

    when:
    def response = subject.create(managedCard)

    then:
    verify(managedCardGateway).create(managedCard) || true
    response.body == managedCard
    HttpStatus.OK == response.statusCode
  }

  def "replace interacts with gateway"() {
    given:
    ManagedCard managedCard = new ManagedCard().tap { setId("mc-123") }
    ManagedCard replacedCard = new ManagedCard().tap { setId("replaced") }
    doReturn(new AccessorResponse<ManagedCard>().withResult(replacedCard)).when(managedCardGateway).replace(managedCard.id)

    when:
    def response = subject.replace(managedCard.id)

    then:
    verify(managedCardGateway).replace(managedCard.id) || true
    response.body == replacedCard
    HttpStatus.OK == response.statusCode
  }

  def "update interacts with gateway"() {
    given:
    ManagedCard managedCard = new ManagedCard().tap {
      setId("mc-123")
      setStatus("status")
    }
    ManagedCard updatedCard = new ManagedCard().tap { setId("new_status") }
    doReturn(new AccessorResponse<ManagedCard>().withResult(updatedCard)).when(managedCardGateway).update(managedCard.id, managedCard)

    when:
    def response = subject.update(managedCard.id, managedCard)

    then:
    verify(managedCardGateway).update(managedCard.id, managedCard) || true
    response.body == updatedCard
    HttpStatus.OK == response.statusCode
  }

  def "activate interacts with gateway"() {
    given:
    ManagedCard managedCard = new ManagedCard()
    doReturn(new AccessorResponse<ManagedCard>().withResult(managedCard)).when(managedCardGateway).activate(managedCard.id)

    when:
    def response = subject.activate(managedCard.id)

    then:
    verify(managedCardGateway).activate(managedCard.id) || true
    response.body == managedCard
    HttpStatus.OK == response.statusCode
  }

  def "set pin interacts with gateway - 202"() {
    given:
    ManagedCard managedCard = new ManagedCard().tap {
      setChallenges(new Challenge[1])
      setPin("1234")
    }
    doReturn(new AccessorResponse<ManagedCard>().withResult(managedCard)).when(managedCardGateway).setPin(managedCard.id, managedCard)

    when:
    def response = subject.setPin(managedCard.id, managedCard)

    then:
    verify(managedCardGateway).setPin(managedCard.id, managedCard) || true
    HttpStatus.ACCEPTED == response.statusCode
  }

  def "set pin interacts with gateway - 204"() {
    given:
    ManagedCard managedCard = new ManagedCard().tap { setPin("1234") }
    doReturn(new AccessorResponse<ManagedCard>().withResult(managedCard)).when(managedCardGateway).setPin(managedCard.id, managedCard)

    when:
    def response = subject.setPin(managedCard.id, managedCard)

    then:
    verify(managedCardGateway).setPin(managedCard.id, managedCard) || true
    HttpStatus.NO_CONTENT == response.statusCode
  }

  def "set pin receives empty result in AccessorResponse object - 204"() {
    given:
    ManagedCard managedCard = new ManagedCard().tap { setPin("1234") }
    doReturn(new AccessorResponse<ManagedCard>().withResult(null)).when(managedCardGateway).setPin(managedCard.id, managedCard)

    when:
    def response = subject.setPin(managedCard.id, managedCard)

    then:
    verify(managedCardGateway).setPin(managedCard.id, managedCard) || true
    HttpStatus.NO_CONTENT == response.statusCode
  }

  def "get unmasked card number interacts with gateway"() {
    given:
    ManagedCard managedCard = new ManagedCard().tap {
      setId("mc-123")
      setUnmaskedNumberOnCard("1234 5678 9000")
    }

    when:
    doReturn(new AccessorResponse<ManagedCard>().withResult(managedCard)).when(managedCardGateway).getUnmaskedCardNumber(managedCard.id)
    def response = subject.getUnmaskedCardNumber(managedCard.id)

    then:
    verify(managedCardGateway).getUnmaskedCardNumber(managedCard.id) || true
    response.body == managedCard
  }
}
