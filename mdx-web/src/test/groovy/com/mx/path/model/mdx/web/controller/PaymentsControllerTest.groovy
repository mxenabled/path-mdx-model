package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.payment.PaymentGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.payment.Payment
import com.mx.path.model.mdx.model.payment.Settings

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class PaymentsControllerTest extends Specification {
  PaymentsController subject
  Gateway gateway
  PaymentGateway paymentGateway

  def setup() {
    paymentGateway = spy(PaymentGateway.builder().build())
    gateway = Gateway.builder().payments(paymentGateway).build()

    subject = new PaymentsController()
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "getPayment interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def payment = new Payment()

    when:
    Mockito.doReturn(new AccessorResponse<Payment>().withResult(payment)).when(paymentGateway).get("id")
    def response = subject.getPayment("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == payment
    verify(paymentGateway).get("id") || true
  }

  def "getPayments interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payment = new Payment()
    def payments = new MdxList<>().tap { add(payment) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Payment>>().withResult(payments)).when(paymentGateway).list()
    def response = subject.getPaymentList()

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == payments
    verify(paymentGateway).list() || true
  }

  def "createPayment interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payment = new Payment()

    when:
    Mockito.doReturn(new AccessorResponse<Payment>().withResult(payment)).when(paymentGateway).create(payment)
    def response = subject.createPayment(payment)

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == payment
    verify(paymentGateway).create(payment) || true
  }

  def "cancelPayment interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>()).when(paymentGateway).cancel("id")
    def response = subject.cancelPayment("id")

    then:
    HttpStatus.NO_CONTENT == response.getStatusCode()
    verify(paymentGateway).cancel("id") || true
  }

  def "updatePayment interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payment = new Payment()

    when:
    Mockito.doReturn(new AccessorResponse<Payment>().withResult(payment)).when(paymentGateway).update("id", payment)
    def response = subject.updatePayment("id", payment)

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == payment
    verify(paymentGateway).update("id", payment) || true
  }

  def "getAccountsUsedForPayments interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def account = new Account()
    def accounts = new MdxList<>().tap { add(account) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(accounts)).when(paymentGateway).accounts()
    def response = subject.getAccountsUsedForPayments()

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == accounts
    verify(paymentGateway).accounts() || true
  }

  def "getPaymentSettings interacts with gateway - 202"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new Settings().tap {
      setChallenges(new ArrayList<Challenge>().tap {
        add(new Challenge())
      })
    }

    when:
    Mockito.doReturn(new AccessorResponse<Settings>().withResult(settings)).when(paymentGateway).settings()
    def response = subject.getPaymentSettings()

    then:
    HttpStatus.ACCEPTED == response.getStatusCode()
    response.getBody() == settings
    verify(paymentGateway).settings() || true
  }

  def "getPaymentSettings interacts with gateway - 200"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new Settings()

    when:
    Mockito.doReturn(new AccessorResponse<Settings>().withResult(settings)).when(paymentGateway).settings()
    def response = subject.getPaymentSettings()

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == settings
    verify(paymentGateway).settings() || true
  }

  def "setPaymentSettings interacts with gateway - 202"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new Settings().tap {
      setChallenges(new ArrayList<Challenge>().tap {
        add(new Challenge())
      })
    }

    when:
    Mockito.doReturn(new AccessorResponse<Settings>().withResult(settings)).when(paymentGateway).updateSettings(settings)
    def response = subject.setPaymentSettings(settings)

    then:
    HttpStatus.ACCEPTED == response.getStatusCode()
    response.getBody() == settings
    verify(paymentGateway).updateSettings(settings) || true
  }

  def "setPaymentSettings interacts with gateway - 200"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new Settings()

    when:
    Mockito.doReturn(new AccessorResponse<Settings>().withResult(settings)).when(paymentGateway).updateSettings(settings)
    def response = subject.setPaymentSettings(settings)

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == settings
    verify(paymentGateway).updateSettings(settings) || true
  }
}
