package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.p2p_transfer.P2PTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.p2p_transfer.P2PTransfer

import org.springframework.http.HttpStatus

import spock.lang.Specification

class P2PTransfersControllerTest extends Specification {
  P2PTransfersController subject
  Gateway gateway
  P2PTransferGateway p2pTransferGateway

  def setup() {
    subject = new P2PTransfersController()
    p2pTransferGateway = mock(P2PTransferGateway)
    gateway = spy(Gateway.builder().clientId("client-1234").p2pTransfers(p2pTransferGateway).build())
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "create interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def p2pTransfer = new P2PTransfer()
    doReturn(new AccessorResponse<P2PTransfer>().withResult(p2pTransfer)).when(p2pTransferGateway).create(p2pTransfer)

    when:
    def result = subject.create(p2pTransfer)

    then:
    HttpStatus.OK == result.statusCode
    result.body == p2pTransfer
    verify(p2pTransferGateway).create(p2pTransfer) || true
  }

  def "create with challenges"() {
    given:
    BaseController.setGateway(gateway)

    def p2pTransfer = new P2PTransfer().tap {
      setChallenges(new MdxList<Challenge>().tap {
        add(new Challenge())
      })
    }
    doReturn(new AccessorResponse<P2PTransfer>().withResult(p2pTransfer)).when(p2pTransferGateway).create(p2pTransfer)

    when:
    def result = subject.create(p2pTransfer)

    then:
    result.statusCode == HttpStatus.ACCEPTED
    result.body == p2pTransfer
    verify(p2pTransferGateway).create(p2pTransfer) || true
  }

  def "cancel interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "transfer-1234"
    doReturn(new AccessorResponse<Void>()).when(p2pTransferGateway).cancel(id)

    when:
    def result = subject.cancel(id)

    then:
    HttpStatus.NO_CONTENT == result.statusCode
    verify(p2pTransferGateway).cancel(id) || true
  }

  def "get interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "transfer-1234"
    def p2pTransfer = new P2PTransfer()
    doReturn(new AccessorResponse<P2PTransfer>().withResult(p2pTransfer)).when(p2pTransferGateway).get(id)

    when:
    def result = subject.get(id)

    then:
    HttpStatus.OK == result.statusCode
    result.body == p2pTransfer
    verify(p2pTransferGateway).get(id) || true
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def p2pTransfers = new MdxList().tap {
      add(new P2PTransfer())
    }
    doReturn(new AccessorResponse<MdxList<P2PTransfer>>().withResult(p2pTransfers)).when(p2pTransferGateway).list()

    when:
    def result = subject.list()

    then:
    HttpStatus.OK == result.statusCode
    result.body == p2pTransfers
    verify(p2pTransferGateway).list() || true
  }

  def "update interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def id = "transfer-1234"
    def p2pTransfer = new P2PTransfer()
    doReturn(new AccessorResponse<P2PTransfer>().withResult(p2pTransfer)).when(p2pTransferGateway).update(id, p2pTransfer)

    when:
    def result = subject.update(id, p2pTransfer)

    then:
    HttpStatus.OK == result.statusCode
    result.body == p2pTransfer
    verify(p2pTransferGateway).update(id, p2pTransfer) || true
  }

  def "update with challenges"() {
    given:
    BaseController.setGateway(gateway)
    def id = "transfer-1234"
    def p2pTransfer = new P2PTransfer().tap {
      setChallenges(new MdxList<Challenge>().tap {
        add(new Challenge())
      })
    }
    doReturn(new AccessorResponse<P2PTransfer>().withResult(p2pTransfer)).when(p2pTransferGateway).update(id, p2pTransfer)

    when:
    def result = subject.update(id, p2pTransfer)

    then:
    result.statusCode == HttpStatus.ACCEPTED
    result.body == p2pTransfer
    verify(p2pTransferGateway).update(id, p2pTransfer) || true
  }
}
