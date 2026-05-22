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
import com.mx.path.gateway.api.payment.PaymentGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.Resources
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.payment.Payment
import com.mx.path.model.mdx.model.payment.Settings

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

import jakarta.servlet.http.HttpServletRequest

class PaymentsControllerTest extends Specification {
  PaymentsController subject
  Gateway gateway
  PaymentGateway paymentGateway
  Gson gson

  def setup() {
    paymentGateway = spy(PaymentGateway.builder().build())
    gateway = Gateway.builder().payments(paymentGateway).build()

    subject = new PaymentsController()

    GsonBuilder builder = new GsonBuilder()
    Resources.registerResources(builder)

    gson = builder.create()
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
    def payments = new MdxList<Payment>().tap { add(payment) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Payment>>().withResult(payments)).when(paymentGateway).list()
    def response = subject.getPaymentList(buildRequest(null, "application/vnd.mx.mdx.v6+json"))

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == payments
    verify(paymentGateway).list() || true
  }

  def "getPayments v20260427 interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payment = new Payment()
    def payments = new MdxList<Payment>().tap { add(payment) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Payment>>().withResult(payments)).when(paymentGateway).list20260427()
    def response = subject.getPaymentList(buildRequest(null, "application/vnd.mx.mdx.v6+json;version=20260427"))

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == payments
    verify(paymentGateway).list20260427() || true
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
    def response = subject.getPaymentSettings(buildRequest(null, "application/vnd.moneydesktop.v2+json"))

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
    def response = subject.getPaymentSettings(buildRequest(null, "application/vnd.moneydesktop.v2+json"))

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
    Mockito.doReturn(new AccessorResponse<Settings>().withResult(settings)).when(paymentGateway).updateSettings(any(Settings.class))
    def response = subject.setPaymentSettings(buildRequest(settings, "application/vnd.moneydesktop.v2+json"))

    then:
    HttpStatus.ACCEPTED == response.getStatusCode()
    response.getBody() == settings
    verify(paymentGateway).updateSettings(any(Settings.class)) || true
  }

  def "setPaymentSettings interacts with gateway - 200"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new Settings()

    when:
    Mockito.doReturn(new AccessorResponse<Settings>().withResult(settings)).when(paymentGateway).updateSettings(any(Settings.class))
    def response = subject.setPaymentSettings(buildRequest(settings, "application/vnd.moneydesktop.v2+json"))

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() == settings
    verify(paymentGateway).updateSettings(any(Settings.class)) || true
  }

  def "getPaymentSettings v20260427 interacts with gateway - 202"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new com.mx.path.model.mdx.model.payment.v20260427.Settings().tap {
      setChallenges(new ArrayList<Challenge>().tap {
        add(new Challenge())
      })
      setSettings(new ArrayList<Challenge>().tap {
        add(new Challenge())
      })
    }

    when:
    Mockito.doReturn(new AccessorResponse<com.mx.path.model.mdx.model.payment.v20260427.Settings>().withResult(settings)).when(paymentGateway).settings20260427()
    def response = subject.getPaymentSettings(buildRequest(null, "application/vnd.moneydesktop.v2+json;version=20260427"))

    then:
    HttpStatus.ACCEPTED == response.getStatusCode()
    response.getBody() instanceof com.mx.path.model.mdx.model.payment.v20260427.Settings
    response.getBody().getSettings() == settings.getSettings()
    verify(paymentGateway).settings20260427() || true
  }

  def "getPaymentSettings v20260427 interacts with gateway - 200"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new com.mx.path.model.mdx.model.payment.v20260427.Settings()

    when:
    Mockito.doReturn(new AccessorResponse<com.mx.path.model.mdx.model.payment.v20260427.Settings>().withResult(settings)).when(paymentGateway).settings20260427()
    def response = subject.getPaymentSettings(buildRequest(null, "application/vnd.moneydesktop.v2+json;version=20260427"))

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() instanceof com.mx.path.model.mdx.model.payment.v20260427.Settings
    response.getBody().getSettings() == settings.getSettings()
    verify(paymentGateway).settings20260427() || true
  }

  def "setPaymentSettings v20260427 interacts with gateway - 202"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new com.mx.path.model.mdx.model.payment.v20260427.Settings().tap {
      setChallenges(new ArrayList<Challenge>().tap {
        add(new Challenge())
      })
      setSettings(new ArrayList<Challenge>().tap {
        add(new Challenge())
      })
    }

    when:
    Mockito.doReturn(new AccessorResponse<com.mx.path.model.mdx.model.payment.v20260427.Settings>().withResult(settings)).when(paymentGateway).updateSettings20260427(any(com.mx.path.model.mdx.model.payment.v20260427.Settings.class))
    def response = subject.setPaymentSettings(buildRequest(settings.wrapped(), "application/vnd.moneydesktop.v2+json;version=20260427"))

    then:
    HttpStatus.ACCEPTED == response.getStatusCode()
    response.getBody() instanceof com.mx.path.model.mdx.model.payment.v20260427.Settings
    response.getBody().getSettings() == settings.getSettings()
    verify(paymentGateway).updateSettings20260427(any(com.mx.path.model.mdx.model.payment.v20260427.Settings.class)) || true
  }

  def "setPaymentSettings v20260427 interacts with gateway - 200"() {
    given:
    BaseController.setGateway(gateway)

    def settings = new com.mx.path.model.mdx.model.payment.v20260427.Settings()

    when:
    Mockito.doReturn(new AccessorResponse<com.mx.path.model.mdx.model.payment.v20260427.Settings>().withResult(settings)).when(paymentGateway).updateSettings20260427(any(com.mx.path.model.mdx.model.payment.v20260427.Settings.class))
    def response = subject.setPaymentSettings(buildRequest(settings, "application/vnd.moneydesktop.v2+json;version=20260427"))

    then:
    HttpStatus.OK == response.getStatusCode()
    response.getBody() instanceof com.mx.path.model.mdx.model.payment.v20260427.Settings
    response.getBody().getSettings() == settings.getSettings()
    verify(paymentGateway).updateSettings20260427(any(com.mx.path.model.mdx.model.payment.v20260427.Settings.class)) || true
  }
}
