package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.external_transfer.ExternalTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.external_transfer.ExternalTransfer

import org.springframework.http.HttpStatus

import spock.lang.Specification

class ExternalTransfersControllerTest extends Specification {
  ExternalTransfersController subject
  Gateway gateway
  ExternalTransferGateway externalTransferGateway

  def setup() {
    subject = new ExternalTransfersController()
    externalTransferGateway = mock(ExternalTransferGateway)
    gateway = spy(Gateway.builder().clientId("client-1234").externalTransfers(externalTransferGateway).build())
    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "create interacts with gateway"() {
    given:
    def transfer = new ExternalTransfer()
    doReturn(new AccessorResponse<ExternalTransfer>().withResult(transfer)).when(externalTransferGateway).create(transfer)

    when:
    def result = subject.create(transfer)

    then:
    result.statusCode == HttpStatus.OK
    result.body == transfer
    verify(externalTransferGateway).create(transfer) || true
  }

  def "create returns 202 when challenged"() {
    given:
    def transfer = new ExternalTransfer().tap {
      setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
    }
    doReturn(new AccessorResponse<ExternalTransfer>().withResult(transfer)).when(externalTransferGateway).create(transfer)

    when:
    def result = subject.create(transfer)

    then:
    result.statusCode == HttpStatus.ACCEPTED
    result.body == transfer
  }

  def "cancel interacts with gateway"() {
    given:
    doReturn(new AccessorResponse<Void>()).when(externalTransferGateway).cancel("transfer-1")

    when:
    def result = subject.cancel("transfer-1")

    then:
    result.statusCode == HttpStatus.NO_CONTENT
    verify(externalTransferGateway).cancel("transfer-1") || true
  }

  def "get interacts with gateway"() {
    given:
    def transfer = new ExternalTransfer()
    doReturn(new AccessorResponse<ExternalTransfer>().withResult(transfer)).when(externalTransferGateway).get("transfer-1")

    when:
    def result = subject.get("transfer-1")

    then:
    result.statusCode == HttpStatus.OK
    result.body == transfer
    verify(externalTransferGateway).get("transfer-1") || true
  }

  def "list interacts with gateway"() {
    given:
    def transfers = new MdxList<ExternalTransfer>().tap {
      add(new ExternalTransfer())
    }
    doReturn(new AccessorResponse<MdxList<ExternalTransfer>>().withResult(transfers)).when(externalTransferGateway).list()

    when:
    def result = subject.list()

    then:
    result.statusCode == HttpStatus.OK
    result.body == transfers
    verify(externalTransferGateway).list() || true
  }

  def "update interacts with gateway"() {
    given:
    def transfer = new ExternalTransfer()
    doReturn(new AccessorResponse<ExternalTransfer>().withResult(transfer)).when(externalTransferGateway).update("transfer-1", transfer)

    when:
    def result = subject.update("transfer-1", transfer)

    then:
    result.statusCode == HttpStatus.OK
    result.body == transfer
    verify(externalTransferGateway).update("transfer-1", transfer) || true
  }

  def "update returns 202 when challenged"() {
    given:
    def transfer = new ExternalTransfer().tap {
      setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
    }
    doReturn(new AccessorResponse<ExternalTransfer>().withResult(transfer)).when(externalTransferGateway).update("transfer-1", transfer)

    when:
    def result = subject.update("transfer-1", transfer)

    then:
    result.statusCode == HttpStatus.ACCEPTED
  }
}
