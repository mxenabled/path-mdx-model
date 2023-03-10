package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.payment.BillGateway
import com.mx.path.gateway.api.payment.PaymentGateway
import com.mx.path.model.mdx.model.payment.Bill
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class BillsControllerTest extends Specification implements WithMockery {
  BillsController subject
  Gateway gateway
  BillGateway billGateway
  PaymentGateway paymentGateway

  def setup() {
    subject = new BillsController()
    billGateway = spy(BillGateway.builder().build())
    paymentGateway = spy(PaymentGateway.builder().bills(billGateway).build())
    gateway = spy(Gateway.builder().payments(paymentGateway).build())
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "getBill interacts with gateway"() {
    given:
    def bill = new Bill()
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Bill>().withResult(bill)).when(billGateway).get("id")
    def response = subject.getBill("id")

    then:
    verify(billGateway).get("id") || true
    response.getBody() == bill
    HttpStatus.OK == response.getStatusCode()
  }

  def "listBills interacts with gateway"() {
    given:
    def bill = new Bill()
    def list = new MdxList<Bill>().tap { add(bill) }
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Bill>>().withResult(list)).when(billGateway).list()
    def response = subject.getBillList()

    then:
    verify(billGateway).list() || true
    HttpStatus.OK == response.getStatusCode()
  }
}
