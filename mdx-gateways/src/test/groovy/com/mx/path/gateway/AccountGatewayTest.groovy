package com.mx.path.gateway

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.TransactionGateway
import com.mx.path.model.mdx.accessor.BaseAccessor
import com.mx.path.model.mdx.accessor.account.AccountBaseAccessor
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.testing.BaseAccessorImpl

import spock.lang.Specification

class AccountGatewayTest extends Specification {
  AccountBaseAccessor accessor
  BaseAccessor baseAccessor
  AccountGateway subject

  def setup() {
    def clientId = "clientId"
    accessor = mock(AccountBaseAccessor)
    baseAccessor = new BaseAccessorImpl()
    baseAccessor.setAccounts(accessor)
    subject = AccountGateway.builder().baseAccessor(baseAccessor).clientId(clientId).build()
  }

  def "all"() {
    given:
    def response = new AccessorResponse<MdxList<Account>>()

    when:
    when(accessor.list()).thenReturn(response)

    then:
    subject.list() == response
  }

  def "delete"() {
    given:
    def response = new AccessorResponse<Account>()

    when:
    when(accessor.delete("A-123")).thenReturn(response)

    then:
    subject.delete("A-123") == response
  }

  def "get"() {
    given:
    def response = new AccessorResponse<Account>()

    when:
    when(accessor.get("A-123")).thenReturn(response)

    then:
    subject.get("A-123") == response
  }

  def "create"() {
    given:
    def response = new AccessorResponse<Account>()
    def account = new Account()

    when:
    when(accessor.create(account)).thenReturn(response)

    then:
    subject.create(account) == response
  }

  def "update"() {
    given:
    def response = new AccessorResponse<Account>()
    def account = new Account()

    when:
    when(accessor.update("id", account)).thenReturn(response)

    then:
    subject.update("id", account) == response
  }

  def "transactions"() {
    given:
    subject = AccountGateway.builder()
        .baseAccessor(baseAccessor)
        .clientId("clientId")
        .transactions(TransactionGateway.builder().baseAccessor(baseAccessor).build())
        .build()

    when:
    def transactions = subject.transactions()

    then:
    transactions.getClass() == TransactionGateway.class
  }
}
