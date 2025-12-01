package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.p2p_transfer.DurationGateway
import com.mx.path.gateway.api.p2p_transfer.P2PTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.p2p_transfer.Duration

import org.springframework.http.HttpStatus

import spock.lang.Specification

class P2PTransferDurationsControllerTest extends Specification {
  P2PTransferDurationsController subject
  Gateway gateway
  P2PTransferGateway p2pTransferGateway
  DurationGateway durationGateway

  def setup() {
    subject = new P2PTransferDurationsController()
    p2pTransferGateway = mock(P2PTransferGateway)
    durationGateway = mock(DurationGateway)

    doReturn(durationGateway).when(p2pTransferGateway).durations()
    gateway = spy(Gateway.builder().clientId("client-1234").p2pTransfers(p2pTransferGateway).build())
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def durations = new MdxList().tap {
      add(new Duration())
    }
    doReturn(new AccessorResponse<MdxList<Duration>>().withResult(durations)).when(durationGateway).list()

    when:
    def result = subject.list()

    then:
    HttpStatus.OK == result.statusCode
    result.body == durations
    verify(durationGateway).list() || true
  }
}
