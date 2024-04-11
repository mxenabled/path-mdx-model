package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.core.context.RequestContext
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.AccountNumbersGateway
import com.mx.path.model.mdx.model.account.AccountNumbers

import spock.lang.Specification

class AccountNumbersControllerTest extends Specification {
  AccountNumbersController subject
  Gateway gateway
  AccountGateway accountGateway
  AccountNumbersGateway accountNumbersGateway

  def setup() {
    def clientId = "client-1234"
    subject = new AccountNumbersController()

    RequestContext.builder().clientId(clientId).build().register()

    accountNumbersGateway = spy(AccountNumbersGateway.builder().clientId(clientId).build())
    accountGateway = spy(AccountGateway.builder().clientId(clientId).accountNumbers(accountNumbersGateway).build())
    gateway = spy(Gateway.builder().clientId(clientId).accounts(accountGateway).build())
  }

  def cleanup() {
    AccountsController.clearGateway()
    RequestContext.clear()
  }

  def "gets on demand account numbers for an account"() {
    given:
    AccountsController.setGateway(gateway)
    def accountNumbers = new AccountNumbers()

    when:
    doReturn(new AccessorResponse<AccountNumbers>().withResult(accountNumbers)).when(accountNumbersGateway).get("A-123")
    def result = subject.getOnDemandAccountNumbers("A-123")

    then:
    verify(accountGateway).accountNumbers() || true
    verify(accountNumbersGateway).get("A-123") || true
    result.body.accountNumbers == accountNumbers
    result.body.id == "A-123"
    RequestContext.current().feature == "accounts"
  }

  def "gets account numbers for an account"() {
    given:
    AccountsController.setGateway(gateway)
    def accountNumbers = new AccountNumbers()

    when:
    doReturn(new AccessorResponse<AccountNumbers>().withResult(accountNumbers)).when(accountNumbersGateway).get("A-123")
    def result = subject.getAccountNumbers("A-123")

    then:
    verify(accountGateway).accountNumbers() || true
    verify(accountNumbersGateway).get("A-123") || true
    result.body == accountNumbers
  }
}
