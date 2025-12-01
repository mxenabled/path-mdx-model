package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.p2p_transfer.AccountGateway
import com.mx.path.gateway.api.p2p_transfer.P2PTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account

import org.springframework.http.HttpStatus

import spock.lang.Specification

class P2PTransferAccountsControllerTest extends Specification {
  P2PTransferAccountsController subject
  Gateway gateway
  P2PTransferGateway p2pTransferGateway
  AccountGateway accountGateway

  def setup() {
    subject = new P2PTransferAccountsController()
    p2pTransferGateway = mock(P2PTransferGateway)
    accountGateway = mock(AccountGateway)

    doReturn(accountGateway).when(p2pTransferGateway).accounts()
    gateway = spy(Gateway.builder().clientId("client-1234").p2pTransfers(p2pTransferGateway).build())
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "list interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def accounts = new MdxList().tap {
      add(new Account())
    }
    doReturn(new AccessorResponse<MdxList<Account>>().withResult(accounts)).when(accountGateway).list()

    when:
    def result = subject.list()

    then:
    HttpStatus.OK == result.statusCode
    result.body == accounts
    verify(accountGateway).list() || true
  }
}
