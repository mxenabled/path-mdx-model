package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.payment.MerchantGateway
import com.mx.path.gateway.api.payment.PaymentGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.payment.Merchant
import com.mx.path.model.mdx.model.payment.MerchantCategory

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class MerchantsControllerTest extends Specification {
  MerchantsController subject

  Gateway gateway
  PaymentGateway paymentGateway
  MerchantGateway merchantGateway

  def setup() {
    subject = new MerchantsController()

    merchantGateway = spy(MerchantGateway.builder().build())
    paymentGateway = PaymentGateway.builder().merchants(merchantGateway).build()
    gateway = Gateway.builder().payments(paymentGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "getMerchantCategoryList interacts with gateway"() {
    given:
    def category = new MerchantCategory()
    def list = new MdxList<>().tap { add(category) }

    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<MerchantCategory>>().withResult(list)).when(merchantGateway).categories()
    def response = subject.getMerchantCategoryList()

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(merchantGateway).categories() || true
    response.getBody() == list
  }

  def "getMerchantsByCategory interacts with gateway"() {
    given:
    def merchant = new Merchant()
    def list = new MdxList<>().tap { add(merchant) }

    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Merchant>>().withResult(list)).when(merchantGateway).search("id")
    def response = subject.getMerchantByCategoryList("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(merchantGateway).search("id") || true
    response.getBody() == list
  }

  def "getMerchantsList interacts with gateway"() {
    given:
    def merchant = new Merchant()
    def list = new MdxList<>().tap { add(merchant) }

    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Merchant>>().withResult(list)).when(merchantGateway).list("name")
    def response = subject.getMerchantList("name")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(merchantGateway).list("name") || true
    response.getBody() == list
  }
}
