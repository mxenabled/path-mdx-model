package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.transfer.AccountGateway
import com.mx.path.gateway.api.transfer.TransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.transfer.options.AccountListOptions
import com.mx.path.model.mdx.web.model.transfer.TransferAccountListQueryParameters

import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class TransferAccountsControllerTest extends Specification {

  TransferAccountsController subject
  Gateway gateway
  TransferGateway transferGateway
  AccountGateway transferAccountGateway

  def setup() {
    subject = new TransferAccountsController()
    transferAccountGateway = spy(AccountGateway.builder().build())
    transferGateway = TransferGateway.builder().accounts(transferAccountGateway).build()
    gateway = Gateway.builder().transfers(transferGateway).build()
  }

  def cleanup() {
    TransfersController.clearRepository()
  }

  def "getAccounts interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def account = new Account()
    def list = new MdxList<Account>().tap { add(account) }
    def queryParameters = new TransferAccountListQueryParameters().tap {
      flow = "flow"
      transfer_type = "transfer_type"
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(list)).when(transferAccountGateway).list(any())
    def response = subject.list(queryParameters)

    then:
    HttpStatus.OK == response.getStatusCode()
    ArgumentCaptor<AccountListOptions> captor = ArgumentCaptor.forClass(AccountListOptions)
    verify(transferAccountGateway).list(captor.capture()) || true
    captor.value.flow == "flow"
    captor.value.transferType == "transfer_type"
  }

  def "getAccountTransfersFrom interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def account = new Account()
    def list = new MdxList<Account>().tap { add(account) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(list)).when(transferAccountGateway).fromAccounts()
    def response = subject.getAccountTransfersFrom()

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(transferAccountGateway).fromAccounts() || true
  }

  def "getAccountTransfersTo interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def account = new Account()
    def list = new MdxList<Account>().tap { add(account) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(list)).when(transferAccountGateway).toAccounts("id")
    def response = subject.getAccountTransfersTo("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(transferAccountGateway).toAccounts("id") || true
  }
}
