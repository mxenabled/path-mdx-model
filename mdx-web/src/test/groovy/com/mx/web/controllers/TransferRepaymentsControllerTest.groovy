package com.mx.web.mdx.controllers

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.transfer.Repayment
import com.mx.models.transfer.options.RepaymentListOptions
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.transfer.RepaymentGateway
import com.mx.path.gateway.api.transfer.TransferGateway
import com.mx.path.testing.WithMockery
import com.mx.web.mdx.models.Transfers.TransferRepaymentsQueryParameters

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class TransferRepaymentsControllerTest extends Specification implements WithMockery {
  TransferRepaymentsController subject
  Gateway gateway
  TransferGateway transferGateway
  RepaymentGateway transferRepaymentGateway

  def setup() {
    subject = new TransferRepaymentsController()
    transferRepaymentGateway = spy(RepaymentGateway.builder().build())
    transferGateway = spy(TransferGateway.builder().repayments(transferRepaymentGateway).build())
    gateway = Gateway.builder().transfers(transferGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "list repayments interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def repayments = new MdxList<Repayment>().tap { add(new Repayment()) }
    def queryParameters = new TransferRepaymentsQueryParameters().tap {
      start_on = "test2"
      frequency_id = "test3"
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Repayment>>().withResult(repayments)).when(transferRepaymentGateway).list(anyString(), any(RepaymentListOptions))
    def response = subject.getRepayments("id", queryParameters)

    then:
    HttpStatus.OK == response.getStatusCode()
    ArgumentCaptor<RepaymentListOptions> captor = ArgumentCaptor.forClass(RepaymentListOptions.class)
    verify(transferGateway).repayments() || true
    verify(transferRepaymentGateway).list(anyString(), captor.capture()) || true
    captor.value.startOn == "test2"
    captor.value.frequencyId == "test3"
  }
}
