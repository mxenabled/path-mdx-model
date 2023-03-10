package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.ach_transfer.AchScheduledTransfer
import com.mx.models.ach_transfer.options.AchScheduledTransferListOptions
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.ach_transfer.AchTransferGateway
import com.mx.path.gateway.api.ach_transfer.ach_scheduled_transfer.AchScheduledTransferGateway
import com.mx.path.gateway.api.ach_transfer.ach_scheduled_transfer.FrequencyGateway
import com.mx.path.model.mdx.web.model.ach_transfer.AchScheduledTransferListQueryParameters
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class AchScheduledTransfersControllerTest extends Specification implements WithMockery {

  AchScheduledTransfersController subject
  Gateway gateway
  FrequencyGateway frequenciesGateway
  AchTransferGateway achTransferGateway
  AchScheduledTransferGateway achScheduledTransferGateway

  def setup() {
    subject = new AchScheduledTransfersController()
    frequenciesGateway = spy(FrequencyGateway.builder().build())
    achScheduledTransferGateway = spy(AchScheduledTransferGateway.builder().frequencies(frequenciesGateway).build())
    achTransferGateway = AchTransferGateway.builder().scheduled(achScheduledTransferGateway).build()
    gateway = Gateway.builder().achTransfers(achTransferGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "create interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def transfer = new AchScheduledTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<AchScheduledTransfer>().withResult(transfer)).when(achScheduledTransferGateway).create(transfer)
    def response = subject.create(transfer)

    then:
    HttpStatus.OK == response.statusCode
    verify(achScheduledTransferGateway).create(transfer) || true
    response.body == transfer
  }

  def "get interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new AchScheduledTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<AchScheduledTransfer>().withResult(transfer)).when(achScheduledTransferGateway).get("id")
    def response = subject.get("id")

    then:
    HttpStatus.OK == response.statusCode
    verify(achScheduledTransferGateway).get("id") || true
    response.body == transfer
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new AchScheduledTransfer()
    def list = new MdxList<AchScheduledTransfer>().tap { add(transfer) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<AchScheduledTransfer>>().withResult(list)).when(achScheduledTransferGateway).list(any(AchScheduledTransferListOptions))
    def response = subject.list(new AchScheduledTransferListQueryParameters())

    then:
    HttpStatus.OK == response.statusCode
    verify(achScheduledTransferGateway).list(any(AchScheduledTransferListOptions)) || true
    response.body == list
  }

  def "cancel interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>()).when(achScheduledTransferGateway).cancel("id")
    def response = subject.cancel("id")

    then:
    HttpStatus.NO_CONTENT == response.statusCode
    verify(achScheduledTransferGateway).cancel("id") || true
  }

  def "update interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new AchScheduledTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<AchScheduledTransfer>().withResult(transfer)).when(achScheduledTransferGateway).update("id", transfer)
    def response = subject.update("id", transfer)

    then:
    HttpStatus.OK == response.statusCode
    verify(achScheduledTransferGateway).update("id", transfer) || true
    response.body == transfer
  }
}
