package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.p2p_transfer.P2PTransferGateway
import com.mx.path.gateway.api.p2p_transfer.SourceGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.p2p_transfer.Source

import org.springframework.http.HttpStatus

import spock.lang.Specification

class P2PTransferSourcesControllerTest extends Specification {
  P2PTransferSourcesController subject
  Gateway gateway
  P2PTransferGateway p2pTransferGateway
  SourceGateway sourceGateway

  def setup() {
    subject = new P2PTransferSourcesController()
    p2pTransferGateway = mock(P2PTransferGateway)
    sourceGateway = mock(SourceGateway)

    doReturn(sourceGateway).when(p2pTransferGateway).sources()
    gateway = spy(Gateway.builder().clientId("client-1234").p2pTransfers(p2pTransferGateway).build())
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def sources = new MdxList().tap {
      add(new Source())
    }
    doReturn(new AccessorResponse<MdxList<Source>>().withResult(sources)).when(sourceGateway).list()

    when:
    def result = subject.list()

    then:
    HttpStatus.OK == result.statusCode
    result.body == sources
    verify(sourceGateway).list() || true
  }
}
