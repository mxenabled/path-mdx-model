package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.cross_account_transfer.CrossAccountRecurringTransfer
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountRecurringTransferGateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountTransferGateway
import com.mx.path.model.mdx.web.controller.BaseController
import com.mx.path.model.mdx.web.controller.CrossAccountRecurringTransferController

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class CrossAccountRecurringTransferControllerTest extends Specification{
  CrossAccountRecurringTransferController subject
  Gateway gateway
  CrossAccountTransferGateway crossAccountTransferGateway
  CrossAccountRecurringTransferGateway crossAccountRecurringTransferGateway

  def setup() {
    subject = new CrossAccountRecurringTransferController()
    crossAccountRecurringTransferGateway = spy(CrossAccountRecurringTransferGateway.builder().build())
    crossAccountTransferGateway = spy(CrossAccountTransferGateway.builder()
        .crossAccountRecurring(crossAccountRecurringTransferGateway).build())
    gateway = Gateway.builder().crossAccount(crossAccountTransferGateway).build()
  }

  def cleanup() {
    CrossAccountRecurringTransferController.clearRepository()
  }

  def "createCrossAccountRecurringTransfer interacts with gateway"() {
    given:
    def crossAccountRecurringTransfer = new CrossAccountRecurringTransfer()

    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<CrossAccountRecurringTransfer>().withResult(crossAccountRecurringTransfer))
        .when(crossAccountRecurringTransferGateway).create(crossAccountRecurringTransfer)
    def response = subject.createRecurringTransfers(crossAccountRecurringTransfer)

    then:
    verify(crossAccountRecurringTransferGateway).create(crossAccountRecurringTransfer) || true
    HttpStatus.OK == response.getStatusCode()
  }

  def "getCrossAccountRecurringTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def crossAccountRecurringTransfer = new CrossAccountRecurringTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<CrossAccountRecurringTransfer>().withResult(crossAccountRecurringTransfer))
        .when(crossAccountRecurringTransferGateway).get("id")
    def response = subject.getCrossAccountRecurringTransfer("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(crossAccountRecurringTransferGateway).get("id") || true
  }

  def "getCrossAccountRecurringTransfers interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def crossAccountRecurringTransfer = new CrossAccountRecurringTransfer()
    def list = new MdxList<CrossAccountRecurringTransfer>().tap { add(crossAccountRecurringTransfer) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<CrossAccountRecurringTransfer>>().withResult(list)).when(crossAccountRecurringTransferGateway).list()
    def response = subject.getCrossAccountRecurringTransfers()

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(crossAccountRecurringTransferGateway).list() || true
  }

  def "updateCrossAccountRecurringTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def crossAccountRecurringTransfer = new CrossAccountRecurringTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<CrossAccountRecurringTransfer>().withResult(crossAccountRecurringTransfer))
        .when(crossAccountRecurringTransferGateway).update("id", crossAccountRecurringTransfer)
    def response = subject.updateCrossAccountRecurringTransfer("id", crossAccountRecurringTransfer)

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(crossAccountRecurringTransferGateway).update("id", crossAccountRecurringTransfer) || true
  }

  def "cancelCrossAccountRecurringTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>()).when(crossAccountRecurringTransferGateway).delete ("id")
    def response = subject.deleteCrossAccountRecurringTransfer("id")

    then:
    HttpStatus.NO_CONTENT == response.getStatusCode()
    verify(crossAccountRecurringTransferGateway).delete("id") || true
  }

  def "skipNextCrossAccountRecurringTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def crossAccountRecurringTransfer = new CrossAccountRecurringTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<CrossAccountRecurringTransfer>().withResult(crossAccountRecurringTransfer))
        .when(crossAccountRecurringTransferGateway).skipNext ("id")
    def response = subject.skipCrossAccountRecurringTransfer("id")

    then:
    HttpStatus.NO_CONTENT == response.getStatusCode()
    verify(crossAccountRecurringTransferGateway).skipNext("id") || true
  }
}
