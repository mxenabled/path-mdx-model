package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.accessors.PathResponseStatus
import com.mx.common.models.MdxList
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountTransferGateway
import com.mx.path.model.mdx.model.AccountType
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.cross_account_transfer.CrossAccountTransfer
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class CrossAccountTransfersControllerTest extends Specification implements WithMockery {
  CrossAccountTransfersController subject
  Gateway gateway
  CrossAccountTransferGateway crossAccountTransferGateway

  def setup() {
    crossAccountTransferGateway = spy(CrossAccountTransferGateway.builder().build())
    gateway = Gateway.builder().crossAccount(crossAccountTransferGateway)
        .build()

    subject = new CrossAccountTransfersController()
  }

  def cleanup() {
    AccountsController.clearRepository()
  }

  def "createCrossAccountTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def crossAccountTransfer = new CrossAccountTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<CrossAccountTransfer>().withResult(crossAccountTransfer)).when(crossAccountTransferGateway).create(crossAccountTransfer)
    def response = subject.createCrossAccountTransfer(crossAccountTransfer)

    then:
    verify(crossAccountTransferGateway).create(crossAccountTransfer) || true
    response.getBody() == crossAccountTransfer
    HttpStatus.OK == response.getStatusCode()
  }

  def "deleteCrossAccountTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def crossAccountTransfer = new CrossAccountTransfer().tap {
      id = "testId"
    }

    when:
    Mockito.doReturn(new AccessorResponse<CrossAccountTransfer>().withResult(crossAccountTransfer)).when(crossAccountTransferGateway).create(crossAccountTransfer)
    Mockito.doReturn(new AccessorResponse<>().withStatus(PathResponseStatus.NO_CONTENT)).when(crossAccountTransferGateway).delete(any())
    def createResponse = subject.createCrossAccountTransfer(crossAccountTransfer)
    def response = subject.deleteCrossAccountTransfer(createResponse.getBody().getId())

    then:
    verify(crossAccountTransferGateway).delete(anyString()) || true
    HttpStatus.NO_CONTENT == response.getStatusCode()
  }

  def "getCrossAccountTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def crossAccountTransfer = new CrossAccountTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<CrossAccountTransfer>().withResult(crossAccountTransfer)).when(crossAccountTransferGateway).get("transfer-123")
    def response = subject.getCrossAccountTransfer("transfer-123")

    then:
    verify(crossAccountTransferGateway).get("transfer-123") || true
    response.getBody() == crossAccountTransfer
    HttpStatus.OK == response.getStatusCode()
  }

  def "listCrossAccountTransfers interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def crossAccountTransfer = new CrossAccountTransfer()
    def crossAccountTransfers = new MdxList<CrossAccountTransfer>().tap { add(crossAccountTransfer) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<CrossAccountTransfer>>().withResult(crossAccountTransfers)).when(crossAccountTransferGateway).list()
    def response = subject.listCrossAccountTransfers()

    then:
    verify(crossAccountTransferGateway).list() || true
    response.getBody() == crossAccountTransfers
    HttpStatus.OK == response.getStatusCode()
  }

  def "listCrossAccountTransferAccountTypes interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def accountType = new AccountType()
    def accountTypes = new MdxList<AccountType>().tap { add(accountType) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<AccountType>>().withResult(accountTypes)).when(crossAccountTransferGateway).accountTypes()
    def response = subject.listCrossAccountTransferAccountTypes()

    then:
    verify(crossAccountTransferGateway).accountTypes() || true
    response.getBody() == accountTypes
    HttpStatus.OK == response.getStatusCode()
  }

  //TODO remove after Synchronicity is rolled over to correct path
  def "listCrossAccountTransferAccountTypesOldPath interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def accountType = new AccountType()
    def accountTypes = new MdxList<AccountType>().tap { add(accountType) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<AccountType>>().withResult(accountTypes)).when(crossAccountTransferGateway).accountTypes()
    def response = subject.listCrossAccountTransferAccountTypesOldPath()

    then:
    verify(crossAccountTransferGateway).accountTypes() || true
    response.getBody() == accountTypes
    HttpStatus.OK == response.getStatusCode()
  }

  def "listCrossAccountTransferFromAccounts interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def account = new Account()
    def accounts = new MdxList<Account>().tap { add(account) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(accounts)).when(crossAccountTransferGateway).accounts()
    def response = subject.listCrossAccountTransferFromAccounts()

    then:
    verify(crossAccountTransferGateway).accounts() || true
    response.getBody() == accounts
    HttpStatus.OK == response.getStatusCode()
  }
}
