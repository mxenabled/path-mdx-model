package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.transfer.TransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.transfer.Transfer
import com.mx.path.model.mdx.model.transfer.options.TransferListOptions
import com.mx.path.model.mdx.web.model.transfer.TransferListQueryParameters

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class TransfersControllerTest extends Specification {

  TransfersController subject
  Gateway gateway
  TransferGateway transferGateway

  def setup() {
    subject = new TransfersController()
    transferGateway = spy(TransferGateway.builder().build())
    gateway = Gateway.builder().transfers(transferGateway).build()
  }

  def cleanup() {
    TransfersController.clearGateway()
  }

  def "createTransfer_implements - 200"() {
    given:
    def transfer = new Transfer()

    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Transfer>().withResult(transfer)).when(transferGateway).create(transfer)
    def response = subject.postTransfers(transfer)

    then:
    verify(transferGateway).create(transfer) || true
    HttpStatus.OK == response.getStatusCode()
  }

  def "createTransfer_implements - 202"() {
    given:
    def challenges = new MdxList<Challenge>().tap { add(new Challenge()) }
    def transfer = new Transfer().tap { setChallenges(challenges)}

    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Transfer>().withResult(transfer)).when(transferGateway).create(transfer)
    def response = subject.postTransfers(transfer)

    then:
    verify(transferGateway).create(transfer) || true
    HttpStatus.ACCEPTED == response.getStatusCode()
  }

  def "getTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def transfer = new Transfer()

    when:
    Mockito.doReturn(new AccessorResponse<Transfer>().withResult(transfer)).when(transferGateway).get("id")
    def response = subject.getTransfer("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(transferGateway).get("id") || true
  }

  def "getTransfers interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new Transfer()
    def list = new MdxList<Transfer>().tap { add(transfer) }
    def queryParameters = new TransferListQueryParameters().tap { transfer_type = "test"}

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Transfer>>().withResult(list)).when(transferGateway).list(any(TransferListOptions))

    def response = subject.list(queryParameters)

    then:
    HttpStatus.OK == response.getStatusCode()
    ArgumentCaptor<TransferListOptions> captor = ArgumentCaptor.forClass(TransferListOptions)
    verify(transferGateway).list(captor.capture()) || true
    captor.value.transferType == "test"
  }

  def "updateTransfer_implements - 200"() {
    given:
    BaseController.setGateway(gateway)

    def transfer = new Transfer()

    when:
    Mockito.doReturn(new AccessorResponse<Transfer>().withResult(transfer)).when(transferGateway).update("id", transfer)
    def response = subject.updateTransfer("id", transfer)

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(transferGateway).update("id", transfer) || true
  }

  def "updateTransfer_implements - 202"() {
    given:
    BaseController.setGateway(gateway)

    def challenges = new MdxList<Challenge>().tap { add(new Challenge()) }
    def transfer = new Transfer().tap { setChallenges(challenges) }

    when:
    Mockito.doReturn(new AccessorResponse<Transfer>().withResult(transfer)).when(transferGateway).update("id", transfer)
    def response = subject.updateTransfer("id", transfer)

    then:
    HttpStatus.ACCEPTED == response.getStatusCode()
    verify(transferGateway).update("id", transfer) || true
  }

  def "cancelTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>()).when(transferGateway).cancel("id")
    def response = subject.cancelTransfer("id")

    then:
    HttpStatus.NO_CONTENT == response.getStatusCode()
    verify(transferGateway).cancel("id") || true
  }

  def "start interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new Transfer()
    transfer.transferType = "test"

    when:
    Mockito.doReturn(new AccessorResponse<Transfer>().withResult(transfer)).when(transferGateway).start(any())
    def response = subject.start(transfer)

    then:
    HttpStatus.OK == response.getStatusCode()
    ArgumentCaptor<Transfer> captor = ArgumentCaptor.forClass(Transfer)
    verify(transferGateway).start(captor.capture()) || true
    captor.value.transferType == "test"
  }

  def "finish interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new Transfer()

    when:
    Mockito.doReturn(new AccessorResponse<Transfer>().withResult(transfer)).when(transferGateway).finish(any(String))
    def response = subject.finish("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(transferGateway).finish(any()) || true
  }
}
