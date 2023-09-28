package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.*

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.AccountStopPaymentReasonsGateway
import com.mx.path.gateway.api.account.AccountStopPaymentsGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.StopPaymentReason
import com.mx.path.testing.WithMockery

import spock.lang.Specification

class AccountStopPaymentReasonsControllerTest extends Specification implements WithMockery {
  AccountStopPaymentReasonsController subject
  Gateway gateway
  AccountStopPaymentReasonsGateway accountStopPaymentReasonsGateway

  def setup() {
    subject = new AccountStopPaymentReasonsController()
    accountStopPaymentReasonsGateway = spy(AccountStopPaymentReasonsGateway.builder().build())

    gateway = Gateway.builder()
        .accounts(AccountGateway.builder()
        .accountStopPayments(AccountStopPaymentsGateway.builder()
        .stopPaymentReasons(accountStopPaymentReasonsGateway)
        .build())
        .build())
        .build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "list interacts with gateway"() {
    given:
    def reasons = new MdxList()
    reasons.add(new StopPaymentReason())

    when:
    doReturn(new AccessorResponse<MdxList<StopPaymentReason>>().withResult(reasons)).when(accountStopPaymentReasonsGateway).list()
    def response = subject.list()

    then:
    verify(accountStopPaymentReasonsGateway).list() || true
    response.body == reasons
  }
}
