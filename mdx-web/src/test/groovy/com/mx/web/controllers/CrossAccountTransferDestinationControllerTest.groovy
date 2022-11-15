package com.mx.web.mdx.controllers

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.cross_account_transfer.DestinationAccount
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountTransferGateway
import com.mx.path.gateway.api.cross_account_transfer.DestinationGateway

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class CrossAccountTransferDestinationControllerTest  extends Specification {
  CrossAccountTransferDestinationController subject
  Gateway gateway
  CrossAccountTransferGateway crossAccountTransferGateway
  DestinationGateway destinationGateway

  def setup() {
    subject = new CrossAccountTransferDestinationController()
    destinationGateway = spy(DestinationGateway.builder().build())
    crossAccountTransferGateway = spy(CrossAccountTransferGateway.builder().destination(destinationGateway).build())
    gateway = Gateway.builder().crossAccount(crossAccountTransferGateway).build()
  }

  def cleanup() {
    CrossAccountTransferDestinationController.clearRepository()
  }

  def "createDestination interacts with gateway"() {
    given:
    def destinationAccount = new DestinationAccount()
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<DestinationAccount>().withResult(destinationAccount))
        .when(destinationGateway).create(destinationAccount)
    def response = subject.create(destinationAccount)

    then:
    verify(destinationGateway).create(destinationAccount) || true
    HttpStatus.OK == response.getStatusCode()
  }

  def "getDestinationAccount interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def destinationAccount = new DestinationAccount()

    when:
    Mockito.doReturn(new AccessorResponse<DestinationAccount>().withResult(destinationAccount)).when(destinationGateway).get("id")
    def response = subject.get("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(destinationGateway).get("id") || true
  }

  def "getDestinationAccounts interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def destinationAccount = new DestinationAccount()
    def list = new MdxList<DestinationAccount>().tap { add(destinationAccount) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<DestinationAccount>>().withResult(list)).when(destinationGateway).list()
    def response = subject.list()

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(destinationGateway).list() || true
  }

  def "updateDestinationAccount interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def destinationAccount = new DestinationAccount()

    when:
    Mockito.doReturn(new AccessorResponse<DestinationAccount>().withResult(destinationAccount))
        .when(destinationGateway).update("id", destinationAccount)
    def response = subject.update("id", destinationAccount)

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(destinationGateway).update("id", destinationAccount) || true
  }

  def "deleteDestinationAccount interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>()).when(destinationGateway).delete("id")
    def response = subject.delete("id")

    then:
    HttpStatus.NO_CONTENT == response.getStatusCode()
    verify(destinationGateway).delete("id") || true
  }
}
