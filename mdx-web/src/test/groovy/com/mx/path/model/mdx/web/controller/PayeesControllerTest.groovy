package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.core.common.accessor.PathResponseStatus
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.payment.PayeeGateway
import com.mx.path.gateway.api.payment.PaymentGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.payment.Payee

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class PayeesControllerTest extends Specification {
  PayeesController subject
  Gateway gateway
  PaymentGateway paymentGateway
  PayeeGateway payeeGateway

  def setup() {
    payeeGateway = spy(PayeeGateway.builder().build())
    paymentGateway = PaymentGateway.builder().payees(payeeGateway).build()
    gateway = Gateway.builder().payments(paymentGateway).build()

    subject = new PayeesController()
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "getPayees interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payee = new Payee()
    def payees = new MdxList<>().tap { add(payee) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Payee>>().withResult(payees)).when(payeeGateway).list()
    def response = subject.getPayeeList()

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(payeeGateway).list() || true
    response.getBody() == payees
  }

  def "getPayee interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def payee = new Payee()

    when:
    Mockito.doReturn(new AccessorResponse<Payee>().withResult(payee)).when(payeeGateway).get("id")
    def response = subject.getPayee("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(payeeGateway).get("id") || true
    response.getBody() == payee
  }

  def "addPayee interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def payee = new Payee()

    when:
    Mockito.doReturn(new AccessorResponse<Payee>().withResult(payee)).when(payeeGateway).create(payee)
    def response = subject.addPayee(payee)

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(payeeGateway).create(payee) || true
    response.getBody() == payee
  }

  def "addPayee interacts with gateway, with status"() {
    given:
    BaseController.setGateway(gateway)
    def payee = new Payee()

    when:
    Mockito.doReturn(new AccessorResponse<Payee>().withResult(payee).withStatus(PathResponseStatus.ACCEPTED)).when(payeeGateway).create(payee)
    def response = subject.addPayee(payee)

    then:
    HttpStatus.ACCEPTED == response.getStatusCode()
    verify(payeeGateway).create(payee) || true
    response.getBody() == payee
  }

  def "deletePayee interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>()).when(payeeGateway).delete("id")
    def response = subject.deletePayee("id")

    then:
    HttpStatus.NO_CONTENT == response.getStatusCode()
    verify(payeeGateway).delete("id") || true
  }

  def "updatePayee interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payee = new Payee()

    when:
    Mockito.doReturn(new AccessorResponse<Payee>().withResult(payee)).when(payeeGateway).update("id", payee)
    def response = subject.updatePayee("id", payee)

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(payeeGateway).update("id", payee) || true
    response.getBody() == payee
  }
}
