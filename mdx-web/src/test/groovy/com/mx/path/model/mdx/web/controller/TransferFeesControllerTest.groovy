package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.transfer.FeeGateway
import com.mx.path.gateway.api.transfer.TransferGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.transfer.Fee
import com.mx.path.model.mdx.model.transfer.options.FeeListOptions
import com.mx.path.model.mdx.web.model.transfer.TransferFeeListQueryParameters
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class TransferFeesControllerTest extends Specification implements WithMockery {
  TransferFeesController subject
  Gateway gateway
  TransferGateway transferGateway
  FeeGateway transferFeeGateway

  def setup() {
    subject = new TransferFeesController()
    transferFeeGateway = spy(FeeGateway.builder().build())
    transferGateway = spy(TransferGateway.builder().fees(transferFeeGateway).build())
    gateway = Gateway.builder().transfers(transferGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "list fees by id interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def fees = new MdxList<Fee>().tap { add(new Fee()) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Fee>>().withResult(fees)).when(transferFeeGateway).list(anyString())
    def response = subject.list("id")

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(transferGateway).fees() || true
    verify(transferFeeGateway).list(anyString()) || true
  }


  def "list fees with options interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def fees = new MdxList<Fee>().tap {
      add(new Fee().tap {
        amount = 1.0
        description = "test description"
      })
    }
    def options = new FeeListOptions().tap {
      amount = 1.0
      toAccountId = "testAccountTypeId"
      fromAccountId = "testFromAccountId"
    }
    def queryParams = new TransferFeeListQueryParameters().tap {
      amount = 1.0
      to_account_id = "testAccountTypeId"
      from_account_id = "testFromAccountId"
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Fee>>().withResult(fees)).when(transferFeeGateway).list(options)
    def response = subject.list(queryParams)

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(transferGateway).fees() || true
    verify(transferFeeGateway).list(options) || true
  }
}
