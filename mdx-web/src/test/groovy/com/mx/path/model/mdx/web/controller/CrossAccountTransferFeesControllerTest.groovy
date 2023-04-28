package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.cross_account_transfer.CrossAccountTransferGateway
import com.mx.path.gateway.api.cross_account_transfer.FeeGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.cross_account_transfer.options.FeeListOptions
import com.mx.path.model.mdx.model.transfer.Fee
import com.mx.path.model.mdx.web.model.cross_account_transfer.CrossAccountTransferFeeListQueryParameters
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
    def fees = new MdxList<Fee>().tap {
      add(new Fee().tap {
        amount = 1.0
        description = "test description"
      })
    }
    def options = new FeeListOptions().tap {
      amount = 1.0
      accountTypeId = "testAccountTypeId"
      destinationId = "testDestId"
      fromAccountId = "testFromAccountId"
      accountTypeNumber = 1
    }
    def queryParams = new CrossAccountTransferFeeListQueryParameters().tap {
      amount = 1.0
      account_type_id = "testAccountTypeId"
      destination_id = "testDestId"
      from_account_id = "testFromAccountId"
      account_type_number = 1
    }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Fee>>().withResult(fees)).when(crossAccountTransferFeeGateway).list(options)
    def response = subject.list(queryParams)

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(crossAccountTransferGateway).fees() || true
    verify(crossAccountTransferFeeGateway).list(options) || true
  }
}
