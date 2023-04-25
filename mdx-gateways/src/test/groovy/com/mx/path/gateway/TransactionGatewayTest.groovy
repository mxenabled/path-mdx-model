package com.mx.path.gateway

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

import com.mx.common.accessors.AccessorConfiguration
import com.mx.common.accessors.AccessorResponse
import com.mx.path.gateway.api.account.TransactionGateway
import com.mx.path.model.mdx.accessor.BaseAccessor
import com.mx.path.model.mdx.accessor.account.AccountBaseAccessor
import com.mx.path.model.mdx.accessor.account.TransactionBaseAccessor
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Transaction
import com.mx.path.model.mdx.model.account.TransactionSearchRequest
import com.mx.path.model.mdx.model.account.TransactionsPage
import com.mx.testing.AccountAccessorImpl
import com.mx.testing.BaseAccessorImpl

import spock.lang.Specification

class TransactionGatewayTest extends Specification {
  TransactionBaseAccessor accessor
  AccountBaseAccessor accountsAccessor
  BaseAccessor baseAccessor
  TransactionGateway subject
  AccessorConfiguration configuration

  def setup() {
    def clientId = "clientId"
    configuration = AccessorConfiguration.builder().clientId(clientId).build()
    accessor = mock(TransactionBaseAccessor)
    accountsAccessor = new AccountAccessorImpl(configuration)

    baseAccessor = new BaseAccessorImpl()
    baseAccessor.setAccounts(accountsAccessor)
    accountsAccessor.setTransactions(accessor)
    subject = TransactionGateway.builder().baseAccessor(baseAccessor).clientId(clientId).build()
  }

  def "recent"() {
    given:
    def response = new AccessorResponse<MdxList<Transaction>>();
    def searchRequest = new TransactionSearchRequest()

    when:
    when(accessor.recent("A-123")).thenReturn(response)

    then:
    subject.recent("A-123") == response
  }

  def "search"() {
    given:
    def response = new AccessorResponse<TransactionsPage>();
    def searchRequest = new TransactionSearchRequest()

    when:
    when(accessor.search("A-123", searchRequest)).thenReturn(response)

    then:
    subject.search("A-123", searchRequest) == response
  }
}
