package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mx.path.core.context.Session
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountRecurringTransferGateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.Resources
import com.mx.path.model.mdx.model.cross_account_transfer.CrossAccountRecurringTransfer

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

import jakarta.servlet.http.HttpServletRequest

class CrossAccountRecurringTransferControllerTest extends Specification{
  CrossAccountRecurringTransferController subject
  Gateway gateway
  CrossAccountTransferGateway crossAccountTransferGateway
  CrossAccountRecurringTransferGateway crossAccountRecurringTransferGateway
  Gson gson

  def setup() {
    subject = new CrossAccountRecurringTransferController()
    crossAccountRecurringTransferGateway = spy(CrossAccountRecurringTransferGateway.builder().build())
    crossAccountTransferGateway = spy(CrossAccountTransferGateway.builder()
        .crossAccountRecurring(crossAccountRecurringTransferGateway).build())
    gateway = Gateway.builder().crossAccount(crossAccountTransferGateway).build()

    GsonBuilder builder = new GsonBuilder()
    Resources.registerResources(builder)
    gson = builder.create()
  }

  def cleanup() {
    CrossAccountRecurringTransferController.clearGateway()
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
    def list = new MdxList<CrossAccountRecurringTransfer>().tap {
      add(crossAccountRecurringTransfer)
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<CrossAccountRecurringTransfer>>().withResult(list)).when(crossAccountRecurringTransferGateway).list()
    def response = subject.getCrossAccountRecurringTransfers(buildRequest(null, "application/vnd.mx.mdx.v6+json"))

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(crossAccountRecurringTransferGateway).list() || true
  }

  def "getCrossAccountRecurringTransfers v20260427 interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def crossAccountRecurringTransfer = new CrossAccountRecurringTransfer()
    def list = new MdxList<CrossAccountRecurringTransfer>().tap {
      add(crossAccountRecurringTransfer)
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<CrossAccountRecurringTransfer>>().withResult(list)).when(crossAccountRecurringTransferGateway).list20260427()
    def response = subject.getCrossAccountRecurringTransfers(buildRequest(null, "application/vnd.mx.mdx.v6+json;version=20260427"))

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(crossAccountRecurringTransferGateway).list20260427() || true
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
    Mockito.doReturn(new AccessorResponse<Void>()).when(crossAccountRecurringTransferGateway).delete("id")
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
        .when(crossAccountRecurringTransferGateway).skipNext("id")
    def response = subject.skipCrossAccountRecurringTransfer("id")

    then:
    HttpStatus.NO_CONTENT == response.getStatusCode()
    verify(crossAccountRecurringTransferGateway).skipNext("id") || true
  }

  def buildRequest(Object body, String contentType) {
    HttpServletRequest request = mock(HttpServletRequest.class)
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(gson.toJson(body))))
    if (Session.current() != null) {
      when(request.getHeader("mx-session-key")).thenReturn(Session.current().getId())
    }
    when(request.getHeaders("Content-Type")).thenReturn(Collections.enumeration([contentType]))
    when(request.getHeaders("Accept")).thenReturn(Collections.enumeration([contentType]))

    return request
  }
}
