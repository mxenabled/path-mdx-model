package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.external_transfer.AccountGateway
import com.mx.path.gateway.api.external_transfer.ExternalAccountGateway
import com.mx.path.gateway.api.external_transfer.ExternalTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.external_transfer.ExternalAccount
import com.mx.path.model.mdx.model.external_transfer.options.AccountListOptions
import com.mx.path.model.mdx.model.external_transfer.options.ExternalAccountListOptions
import com.mx.path.model.mdx.web.model.external_transfer.ExternalAccountListQueryParameters
import com.mx.path.model.mdx.web.model.external_transfer.ExternalTransferAccountListQueryParameters

import org.mockito.ArgumentCaptor
import org.springframework.http.HttpStatus

import spock.lang.Specification

class ExternalAccountsControllerTest extends Specification {
  ExternalAccountsController subject
  Gateway gateway
  AccountGateway accountGateway
  ExternalAccountGateway externalAccountGateway

  def setup() {
    subject = new ExternalAccountsController()
    def externalTransferGateway = mock(ExternalTransferGateway)
    accountGateway = mock(AccountGateway)
    externalAccountGateway = mock(ExternalAccountGateway)
    doReturn(accountGateway).when(externalTransferGateway).accounts()
    doReturn(externalAccountGateway).when(externalTransferGateway).externalAccounts()
    gateway = spy(Gateway.builder().clientId("client-1234").externalTransfers(externalTransferGateway).build())
    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "listHeldAccounts maps query parameters to options"() {
    given:
    def accounts = new MdxList<Account>().tap { add(new Account()) }
    def queryParameters = new ExternalTransferAccountListQueryParameters().tap {
      setAccount_id("ACCOUNT-1")
      setExternal_account_id("EXTERNAL-ACCOUNT-1")
      setList_type("DESTINATION")
    }
    doReturn(new AccessorResponse<MdxList<Account>>().withResult(accounts)).when(accountGateway).list(any(AccountListOptions))

    when:
    def result = subject.listHeldAccounts(queryParameters)

    then:
    result.statusCode == HttpStatus.OK
    result.body == accounts

    def captor = ArgumentCaptor.forClass(AccountListOptions)
    verify(accountGateway).list(captor.capture()) || true
    captor.value.accountId == "ACCOUNT-1"
    captor.value.externalAccountId == "EXTERNAL-ACCOUNT-1"
    captor.value.listType == "DESTINATION"
  }

  def "list maps query parameters to options"() {
    given:
    def externalAccounts = new MdxList<ExternalAccount>().tap {
      add(new ExternalAccount())
    }
    def queryParameters = new ExternalAccountListQueryParameters().tap {
      setAccount_id("ACCOUNT-1")
      setExternal_account_id("EXTERNAL-ACCOUNT-1")
      setList_type("SOURCE")
    }
    doReturn(new AccessorResponse<MdxList<ExternalAccount>>().withResult(externalAccounts)).when(externalAccountGateway).list(any(ExternalAccountListOptions))

    when:
    def result = subject.list(queryParameters)

    then:
    result.statusCode == HttpStatus.OK
    result.body == externalAccounts

    def captor = ArgumentCaptor.forClass(ExternalAccountListOptions)
    verify(externalAccountGateway).list(captor.capture()) || true
    captor.value.accountId == "ACCOUNT-1"
    captor.value.externalAccountId == "EXTERNAL-ACCOUNT-1"
    captor.value.listType == "SOURCE"
  }

  def "get interacts with gateway"() {
    given:
    def externalAccount = new ExternalAccount()
    doReturn(new AccessorResponse<ExternalAccount>().withResult(externalAccount)).when(externalAccountGateway).get("EXTERNAL-ACCOUNT-1")

    when:
    def result = subject.get("EXTERNAL-ACCOUNT-1")

    then:
    result.statusCode == HttpStatus.OK
    result.body == externalAccount
    verify(externalAccountGateway).get("EXTERNAL-ACCOUNT-1") || true
  }

  def "create interacts with gateway"() {
    given:
    def externalAccount = new ExternalAccount()
    doReturn(new AccessorResponse<ExternalAccount>().withResult(externalAccount)).when(externalAccountGateway).create(externalAccount)

    when:
    def result = subject.create(externalAccount)

    then:
    result.statusCode == HttpStatus.OK
    result.body == externalAccount
    verify(externalAccountGateway).create(externalAccount) || true
  }

  def "create returns 202 when challenged"() {
    given:
    def externalAccount = new ExternalAccount().tap {
      setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
    }
    doReturn(new AccessorResponse<ExternalAccount>().withResult(externalAccount)).when(externalAccountGateway).create(externalAccount)

    when:
    def result = subject.create(externalAccount)

    then:
    result.statusCode == HttpStatus.ACCEPTED
  }

  def "update interacts with gateway"() {
    given:
    def externalAccount = new ExternalAccount()
    doReturn(new AccessorResponse<ExternalAccount>().withResult(externalAccount)).when(externalAccountGateway).update("EXTERNAL-ACCOUNT-1", externalAccount)

    when:
    def result = subject.update("EXTERNAL-ACCOUNT-1", externalAccount)

    then:
    result.statusCode == HttpStatus.OK
    result.body == externalAccount
    verify(externalAccountGateway).update("EXTERNAL-ACCOUNT-1", externalAccount) || true
  }

  def "delete interacts with gateway"() {
    given:
    doReturn(new AccessorResponse<Void>()).when(externalAccountGateway).delete("EXTERNAL-ACCOUNT-1")

    when:
    def result = subject.delete("EXTERNAL-ACCOUNT-1")

    then:
    result.statusCode == HttpStatus.NO_CONTENT
    verify(externalAccountGateway).delete("EXTERNAL-ACCOUNT-1") || true
  }
}
