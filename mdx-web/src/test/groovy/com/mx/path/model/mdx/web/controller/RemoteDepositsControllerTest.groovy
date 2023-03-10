package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.remote_deposit.RemoteDepositGateway
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.remote_deposit.RemoteDeposit

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class RemoteDepositsControllerTest extends Specification {
  RemoteDepositsController subject
  Gateway gateway
  RemoteDepositGateway remoteDepositGateway

  def setup() {
    subject = new RemoteDepositsController()
    remoteDepositGateway = spy(RemoteDepositGateway.builder().build())
    gateway = Gateway.builder().remoteDeposits(remoteDepositGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "getRemoteDeposit interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def rdc = new RemoteDeposit()

    when:
    Mockito.doReturn(new AccessorResponse<RemoteDeposit>().withResult(rdc)).when(remoteDepositGateway).get("id")
    def response = subject.getRemoteDeposit("id")

    then:
    verify(remoteDepositGateway).get("id") || true
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == rdc
  }

  def "getRemoteDeposits interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def rdc = new RemoteDeposit()
    def list = new MdxList<>().tap { add(rdc) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<RemoteDeposit>>().withResult(list)).when(remoteDepositGateway).list()
    def response = subject.getRemoteDeposits()

    then:
    verify(remoteDepositGateway).list() || true
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == list
  }

  def "createRemoteDeposit interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def rdc = new RemoteDeposit()

    when:
    Mockito.doReturn(new AccessorResponse<RemoteDeposit>().withResult(rdc)).when(remoteDepositGateway).create(rdc)
    def response = subject.createRemoteDeposit(rdc)

    then:
    verify(remoteDepositGateway).create(rdc) || true
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == rdc
  }

  def "getRemoteDepositAccounts interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def account = new Account()
    def list = new MdxList<>().tap { add(account) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(list)).when(remoteDepositGateway).accounts()
    def response = subject.getRemoteDepositAccounts()

    then:
    verify(remoteDepositGateway).accounts() || true
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == list
  }
}
