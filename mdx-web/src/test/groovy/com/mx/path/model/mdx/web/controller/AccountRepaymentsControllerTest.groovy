package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.transfer.Repayment
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.RepaymentGateway
import com.mx.path.model.mdx.web.controller.AccountRepaymentsController
import com.mx.path.model.mdx.web.controller.BaseController
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class AccountRepaymentsControllerTest extends Specification implements WithMockery {
  AccountRepaymentsController subject
  Gateway gateway
  AccountGateway accountGateway
  RepaymentGateway accountRepaymentGateway

  def setup() {
    subject = new AccountRepaymentsController()
    accountRepaymentGateway = spy(RepaymentGateway.builder().build())
    accountGateway = spy(AccountGateway.builder().repayments(accountRepaymentGateway).build())
    gateway = Gateway.builder().accounts(accountGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "list repayments interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def id = "testId"
    def repayments = new MdxList<Repayment>()

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Repayment>>().withResult(repayments)).when(accountRepaymentGateway).list(id)
    def response = subject.repayments(id)

    then:
    HttpStatus.OK == response.statusCode
    verify(accountGateway).repayments() || true
    verify(accountRepaymentGateway).list(id) || true
    response.body == repayments
  }
}
