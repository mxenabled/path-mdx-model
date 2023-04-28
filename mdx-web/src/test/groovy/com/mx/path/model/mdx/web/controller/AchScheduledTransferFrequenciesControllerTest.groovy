package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.ach_transfer.AchTransferGateway
import com.mx.path.gateway.api.ach_transfer.ach_scheduled_transfer.AchScheduledTransferGateway
import com.mx.path.gateway.api.ach_transfer.ach_scheduled_transfer.FrequencyGateway
import com.mx.path.model.mdx.model.Frequency
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.ach_transfer.options.FrequencyListOptions
import com.mx.path.model.mdx.web.model.ach_transfer.AchScheduledTransferFrequencyListQueryParameters
import com.mx.path.testing.WithMockery

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class AchScheduledTransferFrequenciesControllerTest extends Specification implements WithMockery {

  AchScheduledTransferFrequenciesController subject
  Gateway gateway
  AchTransferGateway achTransferGateway
  AchScheduledTransferGateway achScheduledTransferGateway
  FrequencyGateway achScheduledTransferFrequencyGateway

  def setup() {
    subject = new AchScheduledTransferFrequenciesController()
    achScheduledTransferFrequencyGateway = spy(FrequencyGateway.builder().build())
    achScheduledTransferGateway = spy(AchScheduledTransferGateway.builder().frequencies(achScheduledTransferFrequencyGateway).build())
    achTransferGateway = AchTransferGateway.builder().scheduled(achScheduledTransferGateway).build()
    gateway = Gateway.builder().achTransfers(achTransferGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "getRecurringTransferFrequencies interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def frequency = new Frequency()
    def list = new MdxList<Frequency>().tap { add(frequency) }
    def queryParameters = new AchScheduledTransferFrequencyListQueryParameters().tap { transfer_type = "test" }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Frequency>>().withResult(list)).when(achScheduledTransferFrequencyGateway).list(any())
    def response = subject.list(queryParameters)

    then:
    HttpStatus.OK == response.statusCode
    ArgumentCaptor<FrequencyListOptions> captor = ArgumentCaptor.forClass(FrequencyListOptions)
    verify(achScheduledTransferFrequencyGateway).list(captor.capture()) || true
    captor.value.transferType == "test"
    response.body == list
  }
}
