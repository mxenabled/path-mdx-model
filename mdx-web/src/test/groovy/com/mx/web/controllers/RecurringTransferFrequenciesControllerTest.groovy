package com.mx.web.mdx.controllers

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.Frequency
import com.mx.models.transfer.options.FrequencyListOptions
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.transfer.TransferGateway
import com.mx.path.gateway.api.transfer.recurring_transfer.FrequencyGateway
import com.mx.path.gateway.api.transfer.recurring_transfer.RecurringTransferGateway
import com.mx.path.testing.WithMockery
import com.mx.web.mdx.models.Transfers.RecurringTransferFrequencyListQueryParameters

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class RecurringTransferFrequenciesControllerTest extends Specification implements WithMockery {

  RecurringTransferFrequenciesController subject
  Gateway gateway
  TransferGateway transferGateway
  RecurringTransferGateway recurringTransferGateway
  FrequencyGateway recurringTransferFrequencyGateway

  def setup() {
    subject = new RecurringTransferFrequenciesController()
    recurringTransferFrequencyGateway = spy(FrequencyGateway.builder().build())
    recurringTransferGateway = RecurringTransferGateway.builder().frequencies(recurringTransferFrequencyGateway).build()
    transferGateway = TransferGateway.builder().recurring(recurringTransferGateway).build()
    gateway = Gateway.builder().transfers(transferGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "getRecurringTransferFrequencies interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def frequency = new Frequency()
    def list = new MdxList<Frequency>().tap { add(frequency) }
    def queryParameters = new RecurringTransferFrequencyListQueryParameters().tap {
      transfer_type = "test"
      flow = "flow"
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Frequency>>().withResult(list)).when(recurringTransferFrequencyGateway).list(any())
    def response = subject.list(queryParameters)

    then:
    HttpStatus.OK == response.statusCode
    ArgumentCaptor<FrequencyListOptions> captor = ArgumentCaptor.forClass(FrequencyListOptions)
    verify(recurringTransferFrequencyGateway).list(captor.capture()) || true
    captor.value.transferType == "test"
    captor.value.flow == "flow"
    response.body == list
  }
}
