package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.*

import java.time.LocalDate
import java.time.Month

import com.mx.common.accessors.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.CheckImageGateway
import com.mx.path.gateway.api.account.TransactionGateway
import com.mx.path.model.mdx.web.model.check_image.CheckImagesGetQueryParameters
import com.mx.path.testing.WithMockery

import org.springframework.http.HttpStatus

import  com.mx.path.model.mdx.model.check.CheckImage
import spock.lang.Specification

class CheckImagesControllerTest extends Specification implements WithMockery{
  CheckImagesController subject
  String backCheck
  String frontCheck

  Gateway gateway
  CheckImageGateway checkImageGateway

  def setup() {
    subject = new CheckImagesController()
    checkImageGateway = spy(CheckImageGateway.builder().build())

    gateway = Gateway.builder()
        .accounts(AccountGateway.builder()
        .transactions(TransactionGateway.builder()
        .checkImages(checkImageGateway)
        .build())
        .build())
        .build()

    backCheck = Base64.getEncoder().encodeToString("back-image".getBytes("UTF-8"))
    frontCheck = Base64.getEncoder().encodeToString("front-image".getBytes("UTF-8"))
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "getCheckImages interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    def check = new CheckImage()
    def queryParameters = new CheckImagesGetQueryParameters().tap {
      setAmount(new BigDecimal(12.34))
      setPosted_on(LocalDate.of(2022, Month.JULY, 21))
    }

    when:
    doReturn(new AccessorResponse<>().withResult(check)).when(checkImageGateway).get(any(), any(), any(), any())
    def response = subject.getCheckImages("acct-123", "trans-123", "123", queryParameters)

    then:
    verify(checkImageGateway).get(any(), any(), any(), any()) || true
    HttpStatus.OK == response.getStatusCode()
    response.getBody() instanceof CheckImage
  }
}
