package com.mx.path.model.mdx.web.controller


import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.p2p_transfer.P2PTransferGateway
import com.mx.path.gateway.api.p2p_transfer.RecurringP2PTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.p2p_transfer.RecurringP2PTransfer

import org.springframework.http.HttpStatus

import spock.lang.Specification

class RecurringP2PTransfersControllerTest extends Specification {
  RecurringP2PTransfersController subject
  Gateway gateway
  P2PTransferGateway p2pTransferGateway
  RecurringP2PTransferGateway recurringP2PTransferGateway

  def setup() {
    subject = new RecurringP2PTransfersController()
    p2pTransferGateway = mock(P2PTransferGateway)
    recurringP2PTransferGateway = mock(RecurringP2PTransferGateway)

    doReturn(recurringP2PTransferGateway).when(p2pTransferGateway).recurring()
    gateway = spy(Gateway.builder().clientId("client-1234").p2pTransfers(p2pTransferGateway).build())
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "create interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def p2pTransfer = new RecurringP2PTransfer()
    doReturn(new AccessorResponse<RecurringP2PTransfer>().withResult(p2pTransfer)).when(recurringP2PTransferGateway).create(p2pTransfer)

    when:
    def result = subject.create(p2pTransfer)

    then:
    HttpStatus.OK == result.statusCode
    result.body == p2pTransfer
    verify(recurringP2PTransferGateway).create(p2pTransfer) || true
  }

  def "delete interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "transfer-1234"
    doReturn(new AccessorResponse<Void>()).when(recurringP2PTransferGateway).delete(id)

    when:
    def result = subject.delete(id)

    then:
    HttpStatus.NO_CONTENT == result.statusCode
    verify(recurringP2PTransferGateway).delete(id) || true
  }

  def "get interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "transfer-1234"
    def p2pTransfer = new RecurringP2PTransfer()
    doReturn(new AccessorResponse<RecurringP2PTransfer>().withResult(p2pTransfer)).when(recurringP2PTransferGateway).get(id)

    when:
    def result = subject.get(id)

    then:
    HttpStatus.OK == result.statusCode
    result.body == p2pTransfer
    verify(recurringP2PTransferGateway).get(id) || true
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def p2pTransfers = new MdxList().tap {
      add(new RecurringP2PTransfer())
    }
    doReturn(new AccessorResponse<MdxList<RecurringP2PTransfer>>().withResult(p2pTransfers)).when(recurringP2PTransferGateway).list()

    when:
    def result = subject.list()

    then:
    HttpStatus.OK == result.statusCode
    result.body == p2pTransfers
    verify(recurringP2PTransferGateway).list() || true
  }

  def "update interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "transfer-1234"
    def p2pTransfer = new RecurringP2PTransfer()
    doReturn(new AccessorResponse<RecurringP2PTransfer>().withResult(p2pTransfer)).when(recurringP2PTransferGateway).update(id, p2pTransfer)

    when:
    def result = subject.update(id, p2pTransfer)

    then:
    HttpStatus.OK == result.statusCode
    result.body == p2pTransfer
    verify(recurringP2PTransferGateway).update(id, p2pTransfer) || true
  }
}
