package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.*

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.AccountStopPaymentsGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.StopPayment
import com.mx.path.testing.WithMockery

import spock.lang.Specification

class AccountStopPaymentsControllerTest extends Specification implements WithMockery {
  AccountStopPaymentsController subject
  Gateway gateway
  AccountStopPaymentsGateway accountStopPaymentsGateway

  def setup() {
    subject = new AccountStopPaymentsController()
    accountStopPaymentsGateway = spy(AccountStopPaymentsGateway.builder().build())

    gateway = Gateway.builder()
        .accounts(AccountGateway.builder()
        .accountStopPayments(accountStopPaymentsGateway)
        .build())
        .build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "create interacts with gateway"() {
    given:
    def id = "account-id"
    def stopPayment = new StopPayment()

    when:
    doReturn(new AccessorResponse<StopPayment>().withResult(stopPayment)).when(accountStopPaymentsGateway).create(id, stopPayment)
    def response = subject.create(id, stopPayment)

    then:
    verify(accountStopPaymentsGateway).create(id, stopPayment) || true
    response.body == stopPayment
  }

  def "list interacts with gateway"() {
    given:
    def id = "account-id"
    def stopPayments = new MdxList()
    stopPayments.add(new StopPayment())

    when:
    doReturn(new AccessorResponse<MdxList<StopPayment>>().withResult(stopPayments)).when(accountStopPaymentsGateway).list(id)
    def response = subject.list(id)

    then:
    verify(accountStopPaymentsGateway).list(id) || true
    response.body == stopPayments
  }
}
