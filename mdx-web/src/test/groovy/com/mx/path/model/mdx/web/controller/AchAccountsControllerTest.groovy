package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.ach_transfer.AccountGateway
import com.mx.path.gateway.api.ach_transfer.AchAccountGateway
import com.mx.path.gateway.api.ach_transfer.AchTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.ach_transfer.AchAccount
import com.mx.path.model.mdx.model.ach_transfer.options.AccountListOptions
import com.mx.path.model.mdx.model.ach_transfer.options.AchAccountListOptions
import com.mx.path.model.mdx.web.model.ach_transfer.AchTransferAccountListQueryParameters
import com.mx.path.model.mdx.web.model.ach_transfer.AchTransferAchAccountListQueryParameters

import org.mockito.ArgumentCaptor
import org.springframework.http.HttpStatus

import spock.lang.Specification

class AchAccountsControllerTest extends Specification {
  AchAccountsController subject
  Gateway gateway
  AchAccountGateway achAccountGateway
  AccountGateway accountGateway

  def testTransferType = "test"
  def testType = "list_type"
  def testAccountId = "account_id"
  def testAchAccountId = "achAccount_id"

  def setup() {
    def clientId = "client-1234"
    subject = new AchAccountsController()

    def achTransferGateway = mock(AchTransferGateway)
    achAccountGateway = mock(AchAccountGateway)
    accountGateway = mock(AccountGateway)

    doReturn(achAccountGateway).when(achTransferGateway).achAccounts()
    doReturn(accountGateway).when(achTransferGateway).accounts()
    gateway = spy(Gateway.builder().clientId(clientId).achTransfers(achTransferGateway).build())
  }

  def "listHeldAccounts interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def heldAccounts = new MdxList<Account>()
    heldAccounts.add(new Account())
    def queryParameters = new AchTransferAccountListQueryParameters();
    queryParameters.setTransfer_type(testTransferType)
    queryParameters.setList_type(testType)
    queryParameters.setAccount_id(testAccountId)
    queryParameters.setAchAccount_id(testAchAccountId)

    when:
    doReturn(new AccessorResponse<MdxList<Account>>().withResult(heldAccounts))
        .when(accountGateway)
        .list(any(AccountListOptions))

    def result = subject.listHeldAccounts(queryParameters)

    then:
    HttpStatus.OK == result.getStatusCode()
    ArgumentCaptor<AccountListOptions> captor = ArgumentCaptor.forClass(AccountListOptions)
    verify(accountGateway).list(captor.capture()) || true
    result.getBody() == heldAccounts
    captor.value.transferType == testTransferType
    captor.value.listType == testType
    captor.value.accountId == testAccountId
    captor.value.achAccountId == testAchAccountId
  }

  def "list interacts with gateway"() {
    given:

    BaseController.setGateway(gateway)
    def achAccounts = new MdxList<AchAccount>()
    achAccounts.add(new AchAccount())
    def queryParameters = new AchTransferAchAccountListQueryParameters().tap {
      transfer_type = testTransferType
      list_type = testType
      account_id = testAccountId
      achAccount_id = testAchAccountId
    }

    when:
    doReturn(new AccessorResponse<MdxList<AchAccount>>().withResult(achAccounts))
        .when(achAccountGateway)
        .list(any(AchAccountListOptions))

    def result = subject.list(queryParameters)

    then:
    HttpStatus.OK == result.getStatusCode()
    ArgumentCaptor<AchAccountListOptions> captor = ArgumentCaptor.forClass(AchAccountListOptions)
    verify(achAccountGateway).list(captor.capture()) || true
    result.getBody() == achAccounts
    captor.value.transferType == testTransferType
    captor.value.listType == testType
    captor.value.accountId == testAccountId
    captor.value.achAccountId == testAchAccountId
  }

  def "get interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def achAccount = new AchAccount()

    when:
    doReturn(new AccessorResponse<AchAccount>().withResult(achAccount))
        .when(achAccountGateway)
        .get("id")

    def result = subject.get("id")

    then:
    HttpStatus.OK == result.getStatusCode()
    verify(achAccountGateway).get("id") || true
    result.getBody() == achAccount
  }

  def "create interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def achAccount = new AchAccount()

    when:
    doReturn(new AccessorResponse<AchAccount>().withResult(achAccount))
        .when(achAccountGateway)
        .create(achAccount)

    def result = subject.create(achAccount)

    then:
    HttpStatus.OK == result.getStatusCode()
    verify(achAccountGateway).create(achAccount) || true
    result.getBody() == achAccount
  }

  def "update interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def achAccount = spy(new AchAccount())

    when:
    doReturn(new AccessorResponse<AchAccount>().withResult(achAccount))
        .when(achAccountGateway)
        .update("id", achAccount)

    def result = subject.update("id", achAccount)

    then:
    HttpStatus.OK == result.getStatusCode()
    verify(achAccountGateway).update("id", achAccount) || true
    verify(achAccount).setId("id") || true
    result.getBody() == achAccount
  }

  def "delete interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    doReturn(new AccessorResponse<Void>())
        .when(achAccountGateway)
        .delete("id")

    def result = subject.delete("id")

    then:
    HttpStatus.NO_CONTENT == result.getStatusCode()
    verify(achAccountGateway).delete("id") || true
  }
}