package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mx.path.core.context.Session
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.transfer.TransferGateway
import com.mx.path.gateway.api.transfer.recurring_transfer.RecurringTransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.Resources
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.profile.ChallengeQuestions
import com.mx.path.model.mdx.model.transfer.RecurringTransfer
import com.mx.path.model.mdx.model.transfer.options.RecurringTransferListOptions
import com.mx.path.model.mdx.web.model.transfer.RecurringTransferListQueryParameters
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

import jakarta.servlet.http.HttpServletRequest

class RecurringTransfersControllerTest extends Specification implements WithMockery {

  RecurringTransfersController subject
  Gateway gateway
  TransferGateway transferGateway
  RecurringTransferGateway recurringTransferGateway
  Gson gson

  def setup() {
    subject = new RecurringTransfersController()
    recurringTransferGateway = spy(RecurringTransferGateway.builder().build())
    transferGateway = TransferGateway.builder().recurring(recurringTransferGateway).build()
    gateway = Gateway.builder().transfers(transferGateway).build()

    GsonBuilder builder = new GsonBuilder()
    Resources.registerResources(builder)
    gson = builder.create()
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "createRecurringTransfer_implemented response is 202"() {
    given:
    BaseController.setGateway(gateway)

    def transfer = new RecurringTransfer()
    def mockResponse = new AccessorResponse<RecurringTransfer>().withResult(
        new RecurringTransfer().tap {
          setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
        }
        )

    when:
    Mockito.doReturn(mockResponse).when(recurringTransferGateway).create(transfer)
    def response = subject.postRecurringTransfers(transfer)

    then:
    HttpStatus.ACCEPTED == response.statusCode
    verify(recurringTransferGateway).create(transfer) || true
    response.body == mockResponse.result
  }

  def "createRecurringTransfer_implemented response is 200"() {
    given:
    BaseController.setGateway(gateway)

    def transfer = new RecurringTransfer()
    def mockResponse = new AccessorResponse<RecurringTransfer>().withResult(transfer)

    when:
    Mockito.doReturn(mockResponse).when(recurringTransferGateway).create(transfer)
    def response = subject.postRecurringTransfers(transfer)

    then:
    HttpStatus.OK == response.statusCode
    verify(recurringTransferGateway).create(transfer) || true
    response.body == mockResponse.result
  }

  def "getRecurringTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new RecurringTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<RecurringTransfer>().withResult(transfer)).when(recurringTransferGateway).get("id")
    def response = subject.getRecurringTransfer("id")

    then:
    HttpStatus.OK == response.statusCode
    verify(recurringTransferGateway).get("id") || true
    response.body == transfer
  }

  def "getRecurringTransfers interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new RecurringTransfer()
    def list = new MdxList<RecurringTransfer>().tap { add(transfer) }
    def queryParameters = new RecurringTransferListQueryParameters().tap {
      transfer_type = "test"
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<RecurringTransfer>>().withResult(list)).when(recurringTransferGateway).list(any(RecurringTransferListOptions))
    def response = subject.list(queryParameters, buildRequest(null, "application/vnd.mx.mdx.v6+json"))

    then:
    HttpStatus.OK == response.statusCode
    verify(recurringTransferGateway).list(any(RecurringTransferListOptions)) || true
    response.body == list
  }

  def "getRecurringTransfers v20260427 interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new RecurringTransfer()
    def list = new MdxList<RecurringTransfer>().tap { add(transfer) }
    def queryParameters = new RecurringTransferListQueryParameters().tap {
      transfer_type = "test"
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<RecurringTransfer>>().withResult(list)).when(recurringTransferGateway).list20260427(any(RecurringTransferListOptions))
    def response = subject.list(queryParameters, buildRequest(null, "application/vnd.mx.mdx.v6+json;version=20260427"))

    then:
    HttpStatus.OK == response.statusCode
    verify(recurringTransferGateway).list20260427(any(RecurringTransferListOptions)) || true
    response.body == list
  }

  def "skipNextRecurringTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def recurringTransfer = new RecurringTransfer()

    when:
    Mockito.doReturn(new AccessorResponse<RecurringTransfer>().withResult(recurringTransfer)).when(recurringTransferGateway).skipNext("id")
    def response = subject.skipNextRecurringTransfer("id")

    then:
    HttpStatus.OK == response.statusCode
    verify(recurringTransferGateway).skipNext("id") || true
    response.body == recurringTransfer
  }

  def "deleteRecurringTransfer interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>()).when(recurringTransferGateway).delete("id")
    def response = subject.deleteRecurringTransfer("id")

    then:
    HttpStatus.NO_CONTENT == response.statusCode
    verify(recurringTransferGateway).delete("id") || true
  }

  def "updateRecurringTransfer_implemented response is 202"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new RecurringTransfer()
    def mockResponse = new AccessorResponse<RecurringTransfer>().withResult(
        new RecurringTransfer().tap {
          setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
        }
        )

    when:
    Mockito.doReturn(mockResponse).when(recurringTransferGateway).update("id", transfer)
    def response = subject.updateRecurringTransfer("id", transfer)

    then:
    HttpStatus.ACCEPTED == response.statusCode
    verify(recurringTransferGateway).update("id", transfer) || true
    response.body == mockResponse.result
  }

  def "updateRecurringTransfer_implemented response is 200"() {
    given:
    BaseController.setGateway(gateway)
    def transfer = new RecurringTransfer()
    def mockResponse = new AccessorResponse<RecurringTransfer>().withResult(transfer)

    when:
    Mockito.doReturn(mockResponse).when(recurringTransferGateway).update("id", transfer)
    def response = subject.updateRecurringTransfer("id", transfer)

    then:
    HttpStatus.OK == response.statusCode
    verify(recurringTransferGateway).update("id", transfer) || true
    response.body == mockResponse.result
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
