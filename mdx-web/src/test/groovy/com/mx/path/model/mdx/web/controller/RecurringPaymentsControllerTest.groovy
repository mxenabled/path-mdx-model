package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.payment.PaymentGateway
import com.mx.path.gateway.api.payment.RecurringPaymentGateway
import com.mx.path.model.mdx.model.Frequency
import com.mx.path.model.mdx.model.payment.RecurringPayment

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class RecurringPaymentsControllerTest extends Specification {
  RecurringPaymentsController subject
  Gateway gateway
  PaymentGateway paymentGateway
  RecurringPaymentGateway recurringPaymentGateway

  def setup() {
    subject = new RecurringPaymentsController()
    recurringPaymentGateway = spy(RecurringPaymentGateway.builder().build())
    paymentGateway = PaymentGateway.builder().recurring(recurringPaymentGateway).build()
    gateway = Gateway.builder().payments(paymentGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
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
    def response = subject.getRecurringPayments()

    then:
    verify(recurringPaymentGateway).list() || true
    response.getBody() == recurringPayments
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
}
