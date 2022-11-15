package com.mx.web.mdx.controllers

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.models.account.AccountNumbers
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.AccountNumberGateway

import spock.lang.Specification

class AccountNumbersControllerTest extends Specification {
  AccountNumbersController subject
  Gateway gateway
  AccountGateway accountGateway
  AccountNumberGateway accountNumberGateway

  def setup() {
    def clientId = "client-1234"
    subject = new AccountNumbersController()

    accountNumberGateway = spy(AccountNumberGateway.builder().clientId(clientId).build())
    accountGateway = spy(AccountGateway.builder().clientId(clientId).accountNumbers(accountNumberGateway).build())
    gateway = spy(Gateway.builder().clientId(clientId).accounts(accountGateway).build())
  }

  def cleanup() {
    AccountsController.clearRepository()
  }

  def "gets account numbers for an account"() {
    given:
    AccountsController.setGateway(gateway)
    def accountNumbers = new AccountNumbers()

    when:
    doReturn(new AccessorResponse<AccountNumbers>().withResult(accountNumbers)).when(accountNumberGateway).get("A-123")
    def result = subject.get("A-123")

    then:
    verify(accountGateway).accountNumbers() || true
    verify(accountNumberGateway).get("A-123") || true
    result.getBody() == accountNumbers
  }
}
