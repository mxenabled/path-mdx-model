package com.mx.web.mdx.controllers

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.models.authorization.Authorization
import com.mx.models.authorization.HtmlPage
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.authorization.AuthorizationGateway

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class AuthorizationsControllerTest extends Specification {
  AuthorizationsController subject
  Gateway gateway
  AuthorizationGateway authorizationGateway

  def setup() {
    authorizationGateway = spy(AuthorizationGateway.builder().build())
    gateway = spy(Gateway.builder().authorizations(authorizationGateway).build())
    subject = new AuthorizationsController()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "createAuthorization interacts with gateway"() {
    given:
    def authorization = new Authorization()
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<Authorization>().withResult(authorization)).when(authorizationGateway).create(authorization)
    def response = subject.createAuthorization(authorization)

    then:
    verify(authorizationGateway).create(authorization) || true
    HttpStatus.OK == response.getStatusCode()
  }

  def "callback interacts with gateway"() {
    given:
    def authorization = new HtmlPage()
    BaseController.setGateway(gateway)

    when:
    Mockito.doReturn(new AccessorResponse<HtmlPage>().withResult(authorization)).when(authorizationGateway).callback("token123")
    def response = subject.callback("token123")

    then:
    verify(authorizationGateway).callback("token123") || true
    HttpStatus.OK == response.getStatusCode()
  }
}
