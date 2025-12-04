package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.p2p_transfer.P2PTransferGateway
import com.mx.path.gateway.api.p2p_transfer.RecipientGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.p2p_transfer.Recipient

import org.springframework.http.HttpStatus

import spock.lang.Specification

class P2PTransferRecipientsControllerTest extends Specification {
  P2PTransferRecipientsController subject
  Gateway gateway
  P2PTransferGateway p2pTransferGateway
  RecipientGateway recipientGateway

  def setup() {
    subject = new P2PTransferRecipientsController()
    p2pTransferGateway = mock(P2PTransferGateway)
    recipientGateway = mock(RecipientGateway)

    doReturn(recipientGateway).when(p2pTransferGateway).recipients()
    gateway = spy(Gateway.builder().clientId("client-1234").p2pTransfers(p2pTransferGateway).build())
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "create interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def recipient = new Recipient()
    doReturn(new AccessorResponse<Recipient>().withResult(recipient)).when(recipientGateway).create(recipient)

    when:
    def result = subject.create(recipient)

    then:
    HttpStatus.OK == result.statusCode
    result.body == recipient
    verify(recipientGateway).create(recipient) || true
  }

  def "delete interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "id-1234"
    doReturn(new AccessorResponse<Void>()).when(recipientGateway).delete(id)

    when:
    def result = subject.delete(id)

    then:
    HttpStatus.NO_CONTENT == result.statusCode
    verify(recipientGateway).delete(id) || true
  }

  def "get interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "id-1234"
    def recipient = new Recipient()
    doReturn(new AccessorResponse<Recipient>().withResult(recipient)).when(recipientGateway).get(id)

    when:
    def result = subject.get(id)

    then:
    HttpStatus.OK == result.statusCode
    result.body == recipient
    verify(recipientGateway).get(id) || true
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def recipients = new MdxList().tap {
      add(new Recipient())
    }
    doReturn(new AccessorResponse<MdxList<Recipient>>().withResult(recipients)).when(recipientGateway).list()

    when:
    def result = subject.list()

    then:
    HttpStatus.OK == result.statusCode
    result.body == recipients
    verify(recipientGateway).list() || true
  }

  def "update interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "id-1234"
    def recipient = new Recipient()
    doReturn(new AccessorResponse<Recipient>().withResult(recipient)).when(recipientGateway).update(id, recipient)

    when:
    def result = subject.update(id, recipient)

    then:
    HttpStatus.OK == result.statusCode
    result.body == recipient
    verify(recipientGateway).update(id, recipient) || true
  }
}
