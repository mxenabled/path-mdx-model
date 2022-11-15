package com.mx.web.mdx.controllers

import static org.mockito.Mockito.*

import com.mx.common.accessors.AccessorResponse
import com.mx.models.account.Account
import com.mx.models.account.AccountOwner
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.AccountOwnerGateway

import spock.lang.Specification

class AccountOwnersControllerTest extends Specification {
  AccountOwnersController subject
  Gateway gateway
  AccountGateway accountGateway
  AccountOwnerGateway accountOwnerGateway

  def setup() {
    def clientId = "client-1234"
    subject = new AccountOwnersController()

    accountOwnerGateway = spy(AccountOwnerGateway.builder().clientId(clientId).build())
    accountGateway = spy(AccountGateway.builder().clientId(clientId).accountOwners(accountOwnerGateway).build())
    gateway = spy(Gateway.builder().clientId(clientId).accounts(accountGateway).build())
  }

  def cleanup() {
    AccountsController.clearRepository()
  }

  def "gets account owner for an account"() {
    given:
    AccountsController.setGateway(gateway)
    def accountOwner = new AccountOwner()

    when:
    doReturn(new AccessorResponse<AccountOwner>().withResult(accountOwner)).when(accountOwnerGateway).get("A-123")
    def result = subject.get("A-123")

    then:
    verify(accountGateway).accountOwners() || true
    verify(accountOwnerGateway).get("A-123") || true
    result.getBody() == accountOwner
  }
}
