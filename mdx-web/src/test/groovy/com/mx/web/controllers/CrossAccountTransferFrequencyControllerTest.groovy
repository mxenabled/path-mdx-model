package com.mx.web.mdx.controllers

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.Frequency
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountTransferGateway
import com.mx.path.gateway.api.cross_account_transfer.FrequencyGateway

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class CrossAccountTransferFrequencyControllerTest extends Specification {
  CrossAccountTransferFrequencyController subject
  FrequencyGateway frequencyGateway
  CrossAccountTransferGateway crossAccountTransferGateway
  Gateway gateway

  def setup() {
    subject = new CrossAccountTransferFrequencyController()
    frequencyGateway = spy(FrequencyGateway.builder().build())
    crossAccountTransferGateway = spy(CrossAccountTransferGateway.builder().frequency(frequencyGateway).build())
    gateway = Gateway.builder().crossAccount(crossAccountTransferGateway).build()
  }

  def cleanup() {
    CrossAccountTransferFrequencyController.clearRepository()
  }

  def "list Frequencies for cross accounts interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def frequency = new Frequency()
    def frequencyList = new MdxList<Frequency>().tap { add(frequency) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Frequency>>().withResult(frequencyList)).when(frequencyGateway).list()
    def response = subject.list()

    then:
    verify(frequencyGateway).list() || true
    response.getBody() == frequencyList
    HttpStatus.OK == response.getStatusCode()
  }
}
