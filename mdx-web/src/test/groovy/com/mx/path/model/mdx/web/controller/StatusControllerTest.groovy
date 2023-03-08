package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.accessors.PathResponseStatus
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.StatusGateway
import com.mx.path.model.mdx.web.controller.BaseController
import com.mx.path.model.mdx.web.controller.StatusController

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class StatusControllerTest extends Specification {

  StatusController subject
  Gateway gateway
  StatusGateway statusGateway

  def setup() {
    subject = new StatusController()
    statusGateway = spy(StatusGateway.builder().build())
    gateway = Gateway.builder().status(statusGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "getStatus interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Void>().withStatus(PathResponseStatus.OK)).when(statusGateway).get()
    def response = subject.getStatus()

    then:
    HttpStatus.OK == response.getStatusCode()
    verify(statusGateway).get() || true
  }
}