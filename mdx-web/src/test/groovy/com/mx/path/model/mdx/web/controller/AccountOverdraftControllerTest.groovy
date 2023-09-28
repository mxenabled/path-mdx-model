package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.*

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.AccountOverdraftGateway
import com.mx.path.model.mdx.model.account.Overdraft
import com.mx.path.testing.WithMockery

import spock.lang.Specification

class AccountOverdraftControllerTest extends Specification implements WithMockery {
  AccountOverdraftController subject
  Gateway gateway
  AccountOverdraftGateway accountOverdraftGateway

  def setup() {
    subject = new AccountOverdraftController()
    accountOverdraftGateway = spy(AccountOverdraftGateway.builder().build())

    gateway = Gateway.builder()
        .accounts(AccountGateway.builder()
        .accountOverdrafts(accountOverdraftGateway)
        .build())
        .build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "get interacts with gateway"() {
    given:
    def id = "account-id"
    def overdraft = new Overdraft()

    when:
    doReturn(new AccessorResponse<Overdraft>().withResult(overdraft)).when(accountOverdraftGateway).get(id)
    def response = subject.get(id)

    then:
    verify(accountOverdraftGateway).get(id) || true
    response.body == overdraft
  }

  def "update interacts with gateway"() {
    given:
    def id = "account-id"
    def overdraft = new Overdraft()

    when:
    doReturn(new AccessorResponse<Overdraft>().withResult(overdraft)).when(accountOverdraftGateway).update(id, overdraft)
    def response = subject.update(id, overdraft)

    then:
    verify(accountOverdraftGateway).update(id, overdraft) || true
    response.body == overdraft
  }
}
