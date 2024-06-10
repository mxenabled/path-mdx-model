package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountAddressGateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.profile.Address
import com.mx.path.testing.WithMockery

import org.springframework.http.HttpStatus

import spock.lang.Specification

class AccountAddressControllerTest extends Specification implements WithMockery {
  AccountAddressController subject
  Gateway gateway
  AccountAddressGateway accountAddressGateway

  def setup() {
    subject = new AccountAddressController()
    accountAddressGateway = spy(AccountAddressGateway.builder().build())
    gateway = Gateway.builder()
        .accounts(AccountGateway.builder()
        .addresses(accountAddressGateway)
        .build())
        .build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "getAccountAddresses"() {
    given:
    AccountAddressController.setGateway(gateway)

    def mockResponse = new AccessorResponse<MdxList<Address>>().withResult(new MdxList<>().tap {
      add(new Address())
    })
    doReturn(mockResponse).when(accountAddressGateway).list()

    when:
    def response = subject.getAllAccountAddresses()

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "getAccountAddress"() {
    given:
    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(accountAddressGateway).get("1")

    when:
    def response = subject.getAccountAddress("1")

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "createAccountAddress"() {
    given:
    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(accountAddressGateway).create(any())

    when:
    def response = subject.createAccountAddress(new Address())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }


  def "updateAccountAddress - 200"() {
    given:
    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(accountAddressGateway).update(any(), any())

    when:
    def response = subject.updateAccountAddress("1", new Address())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "putAddress_implemented - 202"() {
    given:
    def mockResponse = new AccessorResponse<Address>().withResult(new Address().tap {
      setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
    })
    doReturn(mockResponse).when(accountAddressGateway).update(any(), any())

    when:
    def response = subject.updateAccountAddress("1", new Address())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }
}
