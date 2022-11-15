package com.mx.web.mdx.controllers

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.cross_account_transfer.options.FeeListOptions
import com.mx.models.transfer.Fee
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountTransferGateway
import com.mx.path.gateway.api.cross_account_transfer.FeeGateway
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class CrossAccountTransferFeesControllerTest extends Specification implements WithMockery {
  CrossAccountTransferFeesController subject
  Gateway gateway
  CrossAccountTransferGateway crossAccountTransferGateway
  FeeGateway crossAccountTransferFeeGateway

  def setup() {
    subject = new CrossAccountTransferFeesController()
    crossAccountTransferFeeGateway = spy(FeeGateway.builder().build())
    crossAccountTransferGateway = spy(CrossAccountTransferGateway.builder().fees(crossAccountTransferFeeGateway).build())
    gateway = Gateway.builder().crossAccount(crossAccountTransferGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "list fees with options interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def fees = new MdxList<Fee>().tap { add(new Fee()) }
    def options = new FeeListOptions()

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Fee>>().withResult(fees)).when(crossAccountTransferFeeGateway).list(options)
    def response = subject.list(options)

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(crossAccountTransferGateway).fees() || true
    verify(crossAccountTransferFeeGateway).list(options) || true
  }
}
