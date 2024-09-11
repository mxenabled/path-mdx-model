package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.managed_card.ManagedCardGateway
import com.mx.path.gateway.api.managed_card.MerchantGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.managed_cards.Merchant
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class ManagedCardMerchantControllerTest extends Specification implements WithMockery {
  ManagedCardMerchantController subject
  Gateway gateway
  ManagedCardGateway managedCardGateway
  MerchantGateway merchantGateway

  def setup() {
    subject = new ManagedCardMerchantController()
    managedCardGateway = spy(ManagedCardGateway.builder().build())
    merchantGateway = spy(MerchantGateway.builder().build())
    managedCardGateway = spy(ManagedCardGateway.builder().merchants(merchantGateway).build())
    gateway = Gateway.builder().managedCards(managedCardGateway).build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    ManagedCardMerchantController.clearGateway()
  }

  def "getMerchant interacts with gateway"() {
    given:
    def id = "merchant_id"
    def merchant = new Merchant()

    when:
    Mockito.doReturn(new AccessorResponse<Merchant>().withResult(merchant)).when(merchantGateway).get(id)
    def response = subject.get(id)

    then:
    verify(merchantGateway).get(id) || true
    response.statusCode == HttpStatus.OK
    response.body == merchant
  }

  def "listMerchant interacts with gateway"() {
    given:
    def merchant = new Merchant()
    def merchants = new MdxList<Merchant>().tap {
      add(merchant)
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Merchant>>().withResult(merchants)).when(merchantGateway).list()
    def response = subject.list()

    then:
    verify(merchantGateway).list() || true
    response.statusCode == HttpStatus.OK
    response.body == merchants
  }

  def "updateMerchant interacts with gateway"() {
    given:
    def id = "merchant_id"
    def merchant = new Merchant()

    when:
    Mockito.doReturn(new AccessorResponse<Merchant>().withResult(merchant)).when(merchantGateway).update(id, merchant)
    def response = subject.update(id, merchant)

    then:
    verify(merchantGateway).update(id, merchant) || true
    response.statusCode == HttpStatus.OK
    response.body == merchant
  }
}
