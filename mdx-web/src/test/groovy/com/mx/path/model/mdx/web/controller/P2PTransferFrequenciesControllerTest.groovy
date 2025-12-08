package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.p2p_transfer.FrequencyGateway
import com.mx.path.gateway.api.p2p_transfer.P2PTransferGateway
import com.mx.path.gateway.api.p2p_transfer.RecurringP2PTransferGateway
import com.mx.path.model.mdx.model.Frequency
import com.mx.path.model.mdx.model.MdxList

import org.springframework.http.HttpStatus

import spock.lang.Specification

class P2PTransferFrequenciesControllerTest extends Specification {
  P2PTransferFrequenciesController subject
  Gateway gateway
  P2PTransferGateway p2pTransferGateway
  RecurringP2PTransferGateway recurringP2PTransferGateway
  FrequencyGateway frequencyGateway

  def setup() {
    subject = new P2PTransferFrequenciesController()
    p2pTransferGateway = mock(P2PTransferGateway)
    recurringP2PTransferGateway = mock(RecurringP2PTransferGateway)
    frequencyGateway = mock(FrequencyGateway)

    doReturn(recurringP2PTransferGateway).when(p2pTransferGateway).recurring()
    doReturn(frequencyGateway).when(recurringP2PTransferGateway).frequencies()
    gateway = spy(Gateway.builder().clientId("client-1234").p2pTransfers(p2pTransferGateway).build())
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def frequencies = new MdxList().tap {
      add(new Frequency())
    }
    doReturn(new AccessorResponse<MdxList<Frequency>>().withResult(frequencies)).when(frequencyGateway).list()

    when:
    def result = subject.list()

    then:
    HttpStatus.OK == result.statusCode
    result.body == frequencies
    verify(frequencyGateway).list() || true
  }
}
