package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.external_transfer.ExternalTransferGateway
import com.mx.path.gateway.api.external_transfer.FrequencyGateway
import com.mx.path.gateway.api.external_transfer.RecurringExternalTransferGateway
import com.mx.path.model.mdx.model.Frequency
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.external_transfer.RecurringExternalTransfer

import org.springframework.http.HttpStatus

import spock.lang.Specification

class RecurringExternalTransfersControllerTest extends Specification {
  RecurringExternalTransfersController subject
  Gateway gateway
  RecurringExternalTransferGateway recurringGateway
  FrequencyGateway frequencyGateway

  def setup() {
    subject = new RecurringExternalTransfersController()
    def externalTransferGateway = mock(ExternalTransferGateway)
    recurringGateway = mock(RecurringExternalTransferGateway)
    frequencyGateway = mock(FrequencyGateway)
    doReturn(recurringGateway).when(externalTransferGateway).recurring()
    doReturn(frequencyGateway).when(recurringGateway).frequencies()
    gateway = spy(Gateway.builder().clientId("client-1234").externalTransfers(externalTransferGateway).build())
    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "create interacts with gateway"() {
    given:
    def recurring = new RecurringExternalTransfer()
    doReturn(new AccessorResponse<RecurringExternalTransfer>().withResult(recurring)).when(recurringGateway).create(recurring)

    when:
    def result = subject.create(recurring)

    then:
    result.statusCode == HttpStatus.OK
    result.body == recurring
    verify(recurringGateway).create(recurring) || true
  }

  def "create returns 202 when challenged"() {
    given:
    def recurring = new RecurringExternalTransfer().tap {
      setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
    }
    doReturn(new AccessorResponse<RecurringExternalTransfer>().withResult(recurring)).when(recurringGateway).create(recurring)

    when:
    def result = subject.create(recurring)

    then:
    result.statusCode == HttpStatus.ACCEPTED
  }

  def "cancel interacts with gateway"() {
    given:
    doReturn(new AccessorResponse<Void>()).when(recurringGateway).cancel("recurring-1")

    when:
    def result = subject.cancel("recurring-1")

    then:
    result.statusCode == HttpStatus.NO_CONTENT
    verify(recurringGateway).cancel("recurring-1") || true
  }

  def "frequencies interacts with gateway"() {
    given:
    def frequencies = new MdxList<Frequency>().tap {
      add(new Frequency("MONTHLY", "Monthly", "Once a month"))
    }
    doReturn(new AccessorResponse<MdxList<Frequency>>().withResult(frequencies)).when(frequencyGateway).list()

    when:
    def result = subject.frequencies()

    then:
    result.statusCode == HttpStatus.OK
    result.body == frequencies
    verify(frequencyGateway).list() || true
  }

  def "get interacts with gateway"() {
    given:
    def recurring = new RecurringExternalTransfer()
    doReturn(new AccessorResponse<RecurringExternalTransfer>().withResult(recurring)).when(recurringGateway).get("recurring-1")

    when:
    def result = subject.get("recurring-1")

    then:
    result.statusCode == HttpStatus.OK
    result.body == recurring
    verify(recurringGateway).get("recurring-1") || true
  }

  def "list interacts with gateway"() {
    given:
    def list = new MdxList<RecurringExternalTransfer>().tap {
      add(new RecurringExternalTransfer())
    }
    doReturn(new AccessorResponse<MdxList<RecurringExternalTransfer>>().withResult(list)).when(recurringGateway).list()

    when:
    def result = subject.list()

    then:
    result.statusCode == HttpStatus.OK
    result.body == list
    verify(recurringGateway).list() || true
  }

  def "update interacts with gateway"() {
    given:
    def recurring = new RecurringExternalTransfer()
    doReturn(new AccessorResponse<RecurringExternalTransfer>().withResult(recurring)).when(recurringGateway).update("recurring-1", recurring)

    when:
    def result = subject.update("recurring-1", recurring)

    then:
    result.statusCode == HttpStatus.OK
    result.body == recurring
    verify(recurringGateway).update("recurring-1", recurring) || true
  }

  def "update returns 202 when challenged"() {
    given:
    def recurring = new RecurringExternalTransfer().tap {
      setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
    }
    doReturn(new AccessorResponse<RecurringExternalTransfer>().withResult(recurring)).when(recurringGateway).update("recurring-1", recurring)

    when:
    def result = subject.update("recurring-1", recurring)

    then:
    result.statusCode == HttpStatus.ACCEPTED
  }
}
