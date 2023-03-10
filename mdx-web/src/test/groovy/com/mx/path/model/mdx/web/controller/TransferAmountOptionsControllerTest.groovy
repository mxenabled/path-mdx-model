package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.*

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.transfer.AmountOptionGateway
import com.mx.path.gateway.api.transfer.TransferGateway
import com.mx.path.model.mdx.model.transfer.TransferAmountOption
import com.mx.path.model.mdx.web.model.transfer.TransferAmountOptionsListQueryParameters

import org.springframework.http.HttpStatus

import spock.lang.Specification

class TransferAmountOptionsControllerTest extends Specification {
  TransferAmountOptionsController subject
  Gateway gateway
  TransferGateway transferGateway
  AmountOptionGateway transferAmountOptionGateway

  TransferAmountOption transferAmountOption
  MdxList<TransferAmountOption> amountOptionMdxList = new MdxList<>()

  def setup() {
    def clientId = "test_client"
    subject = new TransferAmountOptionsController()
    transferAmountOptionGateway = spy(AmountOptionGateway.builder().clientId(clientId).build())
    transferGateway = spy(TransferGateway.builder().clientId(clientId).transferAmountOptions(transferAmountOptionGateway).build())
    gateway = Gateway.builder().clientId(clientId).transfers(transferGateway).build()
    transferAmountOption = new TransferAmountOption().tap {
      id = "test_id"
      name = "test_name"
      description = "test_description"
      amount = 10.00
      isRecurringEnabled = false
    }
    amountOptionMdxList.add(transferAmountOption)
  }

  def cleanup() {
    TransfersController.clearRepository()
  }

  def "list transfer amount options interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def options = new TransferAmountOptionsListQueryParameters().tap {
      transfer_type = "flow"
      source_account_id = "test_source_id"
      destination_account_id = "test_dest_id"
    }

    when:
    doReturn(new AccessorResponse<MdxList<TransferAmountOption>>().withResult(amountOptionMdxList)).when(transferAmountOptionGateway).list(any())
    def response = subject.list(options)

    then:
    verify(transferAmountOptionGateway).list(any()) || true
    HttpStatus.OK == response.getStatusCode()
    def resultList = response.getBody()
    resultList.get(0).getId() == "test_id"
    resultList.get(0).getDescription() == "test_description"
    resultList.get(0).getName() == "test_name"
  }
}
