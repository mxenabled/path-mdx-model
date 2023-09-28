package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.*

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountDetailsGateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.model.mdx.model.account.AccountDetails
import com.mx.path.testing.WithMockery

import spock.lang.Specification

class AccountDetailsControllerTest extends Specification implements WithMockery {
  AccountDetailsController subject
  Gateway gateway
  AccountDetailsGateway accountDetailsGateway

  def setup() {
    subject = new AccountDetailsController()
    accountDetailsGateway = spy(AccountDetailsGateway.builder().build())

    gateway = Gateway.builder()
        .accounts(AccountGateway.builder()
        .accountDetails(accountDetailsGateway)
        .build())
        .build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "getAccountDetails interacts with gateway"() {
    given:
    def id = "account-id"
    def accountDetails = new AccountDetails()

    when:
    doReturn(new AccessorResponse<AccountDetails>().withResult(accountDetails)).when(accountDetailsGateway).get(id)
    def response = subject.get(id)

    then:
    verify(accountDetailsGateway).get(id) || true
    response.body == accountDetails
  }
}
