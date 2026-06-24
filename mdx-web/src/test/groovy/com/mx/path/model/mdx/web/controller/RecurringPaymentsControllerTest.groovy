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
import com.mx.path.gateway.api.payment.PaymentGateway
import com.mx.path.gateway.api.payment.RecurringPaymentGateway
import com.mx.path.model.mdx.model.Frequency
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.Resources
import com.mx.path.model.mdx.model.payment.RecurringPayment

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

import jakarta.servlet.http.HttpServletRequest

class RecurringPaymentsControllerTest extends Specification {
  RecurringPaymentsController subject
  Gateway gateway
  PaymentGateway paymentGateway
  RecurringPaymentGateway recurringPaymentGateway
  Gson gson

  def setup() {
    subject = new RecurringPaymentsController()
    recurringPaymentGateway = spy(RecurringPaymentGateway.builder().build())
    paymentGateway = PaymentGateway.builder().recurring(recurringPaymentGateway).build()
    gateway = Gateway.builder().payments(paymentGateway).build()

    GsonBuilder builder = new GsonBuilder()
    Resources.registerResources(builder)
    gson = builder.create()
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "index recurring payment frequencies invokes gateway"() {
    given:
    Frequency frequency = new Frequency()
    MdxList<Frequency> frequencies = new MdxList<>()
    frequencies.add(frequency)
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Frequency>>().withResult(frequencies)).when(recurringPaymentGateway).frequencies()
    def response = subject.getRecurringPaymentFrequencies()

    then:
    verify(recurringPaymentGateway).frequencies() || true
    response.getBody() == frequencies
  }

  def "index recurring payments invokes gateway"() {
    given:
    RecurringPayment recurringPayment = new RecurringPayment()
    MdxList<RecurringPayment> recurringPayments = new MdxList<>()
    recurringPayments.add(recurringPayment)
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<RecurringPayment>>().withResult(recurringPayments)).when(recurringPaymentGateway).list()
    def response = subject.getRecurringPayments(buildRequest(null, "application/vnd.mx.mdx.v6+json"))

    then:
    response.body instanceof MdxList<RecurringPayment>
    response.body == recurringPayments
    verify(recurringPaymentGateway).list() || true
  }

  def "index recurring payments v20260427 invokes gateway"() {
    given:
    RecurringPayment recurringPayment = new RecurringPayment()
    MdxList<RecurringPayment> recurringPayments = new MdxList<>()
    recurringPayments.add(recurringPayment)
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<RecurringPayment>>().withResult(recurringPayments)).when(recurringPaymentGateway).list20260427()
    def response = subject.getRecurringPayments(buildRequest(null, "application/vnd.mx.mdx.v6+json;version=20260427"))

    then:
    response.body instanceof MdxList<RecurringPayment>
    response.body == recurringPayments
    verify(recurringPaymentGateway).list20260427() || true
  }

  def "get recurring payment invokes gateway"() {
    given:
    RecurringPayment recurringPayment = new RecurringPayment()
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<RecurringPayment>().withResult(recurringPayment)).when(recurringPaymentGateway).get("rp-123")
    def response = subject.getRecurringPayment("rp-123")

    then:
    verify(recurringPaymentGateway).get("rp-123") || true
    response.getBody() == recurringPayment
  }

  def "create invokes gateway"() {
    given:
    RecurringPayment recurringPayment = new RecurringPayment()
    BaseController.setGateway(gateway)
    Mockito.doReturn(new AccessorResponse<RecurringPayment>().withResult(recurringPayment)).when(recurringPaymentGateway).create(recurringPayment)

    when:
    def response = subject.createRecurringPayment(recurringPayment)

    then:
    verify(recurringPaymentGateway).create(recurringPayment) || true
    response.getBody() == recurringPayment
    HttpStatus.OK == response.getStatusCode()
  }

  def "update invokes gateway"() {
    given:
    RecurringPayment recurringPayment = new RecurringPayment()
    recurringPayment.setId("rp-123")
    recurringPayment.setStatus("status")
    RecurringPayment updatedPayment = new RecurringPayment()
    updatedPayment.setId("new_status")
    BaseController.setGateway(gateway)
    Mockito.doReturn(new AccessorResponse<RecurringPayment>().withResult(updatedPayment)).when(recurringPaymentGateway).update(recurringPayment.id, recurringPayment)

    when:
    def response = subject.updateRecurringPayment(recurringPayment.id, recurringPayment)

    then:
    verify(recurringPaymentGateway).update(recurringPayment.id, recurringPayment) || true
    response.getBody() == updatedPayment
    HttpStatus.OK == response.getStatusCode()
  }

  def "cancel invokes gateway"() {
    given:
    RecurringPayment recurringPayment = new RecurringPayment()
    BaseController.setGateway(gateway)
    Mockito.doReturn(new AccessorResponse<RecurringPayment>().withResult(recurringPayment)).when(recurringPaymentGateway).cancel(recurringPayment.id)

    when:
    def response = subject.cancelRecurringPayment(recurringPayment.id)

    then:
    verify(recurringPaymentGateway).cancel(recurringPayment.id) || true
    HttpStatus.NO_CONTENT == response.getStatusCode()
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
