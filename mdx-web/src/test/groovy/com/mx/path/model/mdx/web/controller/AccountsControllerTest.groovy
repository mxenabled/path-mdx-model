package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.core.context.RequestContext
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.TransactionGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.account.Transaction
import com.mx.path.model.mdx.model.account.TransactionSearchRequest
import com.mx.path.model.mdx.model.account.TransactionsPage
import com.mx.path.model.mdx.model.account.options.TransactionListOptions
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class AccountsControllerTest extends Specification implements WithMockery {
  AccountsController subject

  Gateway gateway
  AccountGateway accountGateway
  TransactionGateway transactionGateway

  def setup() {
    def clientId = "client-1234"
    subject = new AccountsController()

    RequestContext.builder().clientId(clientId).build().register()

    transactionGateway = spy(TransactionGateway.builder().clientId(clientId).build())
    accountGateway = spy(AccountGateway.builder().clientId(clientId).transactions(transactionGateway).build())
    gateway = spy(Gateway.builder().clientId(clientId).accounts(accountGateway).build())
  }

  def cleanup() {
    AccountsController.clearGateway()
    RequestContext.clear()
  }

  def "create when implemented"() {
    given:
    def account = new Account();
    AccountsController.setGateway(gateway)
    Mockito.doReturn(new AccessorResponse<Account>().withResult(account)).when(accountGateway).create(account)

    when:
    def response = subject.createAccount(account)

    then:
    verify(accountGateway).create(account) || true
    response.body == account
    HttpStatus.OK == response.statusCode
  }

  def "delete when implemented"() {
    given:
    AccountsController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>()).when(accountGateway).delete("account-1")
    def response = subject.deleteAccount("account-1")

    then:
    verify(accountGateway).delete("account-1") || true
    HttpStatus.NO_CONTENT == response.statusCode
  }

  def "update when implemented"() {
    given:
    def account = new Account();
    AccountsController.setGateway(gateway)
    Mockito.doReturn(new AccessorResponse<Account>().withResult(account)).when(accountGateway).update("account-1", account)

    when:
    def response = subject.updateAccount(null, null, "account-1", account)

    then:
    verify(accountGateway).update("account-1", account) || true
    HttpStatus.OK == response.statusCode
  }

  def "gets an account"() {
    given:
    AccountsController.setGateway(gateway)
    def account = new Account()

    when:
    Mockito.doReturn(new AccessorResponse<Account>().withResult(account)).when(accountGateway).get("A-123")
    def result = subject.getAccount("A-123")

    then:
    verify(accountGateway).get("A-123") || true
    result.body == account
  }

  def "gets all accounts"() {
    given:
    AccountsController.setGateway(gateway)
    def accounts = new MdxList<Account>()
    accounts.add(new Account())

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(accounts)).when(accountGateway).list()
    def result = subject.getAllAccounts()

    then:
    verify(accountGateway).list() || true
    result.body == accounts
  }

  def "gets all OnDemand accounts"() {
    given:
    AccountsController.setGateway(gateway)
    def accounts = new MdxList<Account>()
    accounts.add(new Account())

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(accounts)).when(accountGateway).list()
    def result = subject.getOnDemandAccounts()

    then:
    verify(accountGateway).list() || true
    result.body.list == accounts
    RequestContext.current().getFeature() == "accounts"
  }

  def "searches transactions"() {
    given:
    AccountsController.setGateway(gateway)
    TransactionSearchRequest searchRequest = new TransactionSearchRequest()
    TransactionsPage page = new TransactionsPage()

    when:
    Mockito.doReturn(new AccessorResponse<TransactionsPage>().withResult(page)).when(transactionGateway).search("A-123", searchRequest)

    searchRequest.setStart_date("2020-01-01")
    def result = subject.searchTransactions(searchRequest, "A-123")

    then:
    verify(transactionGateway).search("A-123", searchRequest) || true
    result.body.transactions == page
    RequestContext.current().feature == "transactions"
  }

  def "get recent transactions"() {
    given:
    AccountsController.setGateway(gateway)
    MdxList<Transaction> transactions = new MdxList<Transaction>()

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Transaction>>().withResult(transactions)).when(transactionGateway).recent("A-123")
    def result = subject.recentTransactions("A-123")

    then:
    verify(transactionGateway).recent("A-123") || true
    result.body == transactions
  }

  def "pending transactions"() {
    given:
    AccountsController.setGateway(gateway)
    MdxList<Transaction> transactions = new MdxList<Transaction>()

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Transaction>>().withResult(transactions)).when(transactionGateway).pending("A-123")
    def result = subject.pendingTransactions("A-123")

    then:
    verify(transactionGateway).pending("A-123") || true
    result.body == transactions
  }

  def "list transactions"() {
    given:

    AccountsController.setGateway(gateway)
    MdxList<Transaction> transactions = new MdxList<Transaction>()
    def checkNumber = "4321"
    def accountId = "A-123"
    TransactionListOptions transactionListOptions = new TransactionListOptions()
    transactionListOptions.checkNumber = checkNumber

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Transaction>>().withResult(transactions)).when(transactionGateway).list(accountId, transactionListOptions)
    def result = subject.listTransactions(accountId, checkNumber)

    then:
    verify(transactionGateway).list(accountId, transactionListOptions) || true
    result.body == transactions
  }
}
