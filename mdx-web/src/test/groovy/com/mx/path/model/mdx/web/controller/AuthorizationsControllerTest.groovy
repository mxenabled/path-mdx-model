package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.core.common.accessor.PathResponseStatus
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.authorization.AuthorizationGateway
import com.mx.path.model.mdx.model.authorization.Authorization
import com.mx.path.model.mdx.model.authorization.HtmlPage
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.challenges.Question

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
    BaseController.clearGateway()
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

  def "createAuthorization - ACCEPTED"() {
    given:
    def authorization = new Authorization()
    BaseController.setGateway(gateway)

    def challenge = new Challenge().tap {
      id = "CHALLENGE_1"
      questions = new ArrayList<Question>().tap {
        add(new Question().tap {
          id = "QUESTION_1"
          prompt = "Do you accept the policy?"
          promptType = "text"
        })
      }
    }
    def accessorResponse = new Authorization().tap {
      challenges = [challenge]
    }
    doReturn(new AccessorResponse<Authorization>()
        .withResult(accessorResponse)
        .withStatus(PathResponseStatus.ACCEPTED))
        .when(authorizationGateway).create(authorization)

    when:
    def response = subject.createAuthorization(authorization)

    then:
    verify(gateway).authorizations() || true
    verify(authorizationGateway).create(authorization) || true
    response.statusCode == HttpStatus.ACCEPTED
    response.getBody().challenges.size() > 0
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
