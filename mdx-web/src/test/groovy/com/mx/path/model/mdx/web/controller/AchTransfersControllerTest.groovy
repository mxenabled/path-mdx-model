package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.ach_transfer.AchTransfer
import com.mx.models.ach_transfer.options.AchTransferListOptions
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.ach_transfer.AchTransferGateway
import com.mx.path.model.mdx.web.controller.AchTransfersController
import com.mx.path.model.mdx.web.controller.BaseController
import com.mx.path.testing.WithMockery
import com.mx.web.mdx.models.AchTransfers.AchTransferListQueryParameters

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class AchTransfersControllerTest extends Specification implements WithMockery {
  AchTransfersController subject

  Gateway gateway
  AchTransferGateway achTransferGateway

  def setup() {
    def clientId = "client-1234"
    subject = new AchTransfersController()
    achTransferGateway = spy(AchTransferGateway.builder().clientId(clientId).build())
    gateway = spy(Gateway.builder().achTransfers(achTransferGateway).build())
  }

  def cleanup() {
    AchTransfersController.clearRepository()
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfers = new MdxList<AchTransfer>()
    transfers.add(new AchTransfer())
    def queryParameters = new AchTransferListQueryParameters().tap { transfer_type = "test" }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<AchTransfer>>().withResult(transfers))
        .when(achTransferGateway)
        .list(any(AchTransferListOptions))
    def result = subject.list(queryParameters)

    then:
    HttpStatus.OK == result.getStatusCode()
    ArgumentCaptor<AchTransferListOptions> captor = ArgumentCaptor.forClass(AchTransferListOptions)
    verify(achTransferGateway).list(captor.capture()) || true
    result.getBody() == transfers
    captor.value.transferType == "test"
  }

  def "get interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new AchTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<AchTransfer>().withResult(transfer))
        .when(achTransferGateway)
        .get("id")
    def result = subject.get("id")

    then:
    HttpStatus.OK == result.getStatusCode()
    verify(achTransferGateway).get("id") || true
    result.getBody() == transfer
  }

  def "create interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new AchTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<AchTransfer>().withResult(transfer))
        .when(achTransferGateway)
        .create(transfer)
    def result = subject.create(transfer)

    then:
    HttpStatus.OK == result.getStatusCode()
    verify(achTransferGateway).create(transfer) || true
    result.getBody() == transfer
  }

  def "update interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = spy(new AchTransfer())

    when:
    Mockito.doReturn(new AccessorResponse<AchTransfer>().withResult(transfer))
        .when(achTransferGateway)
        .update("id", transfer)
    def result = subject.update("id", transfer)

    then:
    HttpStatus.OK == result.getStatusCode()
    verify(achTransferGateway).update("id", transfer) || true
    verify(transfer).setId("id") || true
    result.getBody() == transfer
  }

  def "cancel interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>())
        .when(achTransferGateway)
        .cancel("id")
    def result = subject.cancel("id")

    then:
    HttpStatus.NO_CONTENT == result.getStatusCode()
    verify(achTransferGateway).cancel("id") || true
  }
}
