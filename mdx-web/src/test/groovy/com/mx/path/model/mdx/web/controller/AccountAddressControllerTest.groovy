package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
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

  def accountId = '123456'
  def addressId = '1'

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
    doReturn(mockResponse).when(accountAddressGateway).list(accountId)

    when:
    def response = subject.getAllAccountAddresses(accountId)

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "getAccountAddress"() {
    given:
    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(accountAddressGateway).get(accountId, addressId)

    when:
    def response = subject.getAccountAddress(accountId, addressId)

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "createAccountAddress"() {
    given:
    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(accountAddressGateway).create(eq(accountId), any())

    when:
    def response = subject.createAccountAddress(accountId, new Address())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }


  def "updateAccountAddress - 200"() {
    given:
    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(accountAddressGateway).update(eq(accountId), eq(addressId), any())

    when:
    def response = subject.updateAccountAddress(accountId, addressId, new Address())

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
    doReturn(mockResponse).when(accountAddressGateway).update(eq(accountId), eq(addressId), any())

    when:
    def response = subject.updateAccountAddress(accountId, addressId, new Address())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }
}
