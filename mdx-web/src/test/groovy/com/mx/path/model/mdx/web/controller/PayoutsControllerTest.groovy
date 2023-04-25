package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.payout.PayoutGateway
import com.mx.path.gateway.api.payout.PayoutMethodGateway
import com.mx.path.gateway.api.payout.RecipientGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.payout.Payout
import com.mx.path.model.mdx.model.payout.PayoutMethod
import com.mx.path.model.mdx.model.payout.Recipient
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class PayoutsControllerTest extends Specification implements WithMockery {

  Gateway gateway
  PayoutGateway payoutGateway
  RecipientGateway recipientGateway
  PayoutMethodGateway payoutMethodGateway
  PayoutsController subject

  def setup() {
    subject = new PayoutsController()
    payoutMethodGateway = spy(PayoutMethodGateway.builder().build())
    recipientGateway = spy(RecipientGateway.builder().payoutMethods(payoutMethodGateway).build())
    payoutGateway = spy(PayoutGateway.builder().recipients(recipientGateway).build())
    gateway = Gateway.builder().payouts(payoutGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "ListPayoutFromAccounts interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def accounts = new MdxList<Account>()
    Mockito.doReturn(new AccessorResponse<MdxList<Account>>().withResult(accounts)).when(payoutGateway).accounts()

    when:
    def response = subject.listPayoutFromAccounts()

    then:
    verify(payoutGateway).accounts() || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == accounts
  }

  def "CreatePayout interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payout = new Payout()
    Mockito.doReturn(new AccessorResponse<Payout>().withResult(payout)).when(payoutGateway).create(payout)

    when:
    def response = subject.createPayout(payout)

    then:
    verify(payoutGateway).create(payout) || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == payout
  }

  def "CancelPayout interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    Mockito.doReturn(new AccessorResponse<Void>()).when(payoutGateway).cancel("payout_id")

    when:
    def response = subject.cancelPayout("payout_id")

    then:
    noExceptionThrown()
    verify(payoutGateway).cancel("payout_id") || true
    response.getStatusCode() == HttpStatus.NO_CONTENT
  }

  def "ListPayouts interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payouts = new MdxList<Payout>()
    Mockito.doReturn(new AccessorResponse<MdxList<Payout>>().withResult(payouts)).when(payoutGateway).list()

    when:
    def response = subject.listPayouts()

    then:
    verify(payoutGateway).list() || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == payouts
  }

  def "GetPayout interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payout = new Payout()
    Mockito.doReturn(new AccessorResponse<Payout>().withResult(payout)).when(payoutGateway).get("payout_id")

    when:
    def response = subject.getPayout("payout_id")

    then:
    verify(payoutGateway).get("payout_id") || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == payout
  }

  def "CreateRecipient interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def recipient = new Recipient()
    Mockito.doReturn(new AccessorResponse<Recipient>().withResult(recipient)).when(recipientGateway).create(recipient)

    when:
    def response = subject.createRecipient(recipient)

    then:
    verify(recipientGateway).create(recipient) || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == recipient
  }

  def "UpdateRecipient interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def recipient = new Recipient()
    Mockito.doReturn(new AccessorResponse<Recipient>().withResult(recipient)).when(recipientGateway).update("recipient_id", recipient)

    when:
    def response = subject.updateRecipient("recipient_id", recipient)

    then:
    verify(recipientGateway).update("recipient_id", recipient) || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == recipient
  }

  def "ListRecipients interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def recipients = new MdxList<Recipient>()
    Mockito.doReturn(new AccessorResponse<MdxList<Recipient>>().withResult(recipients)).when(recipientGateway).list(true)

    when:
    def response = subject.listRecipients(true)

    then:
    verify(recipientGateway).list(true) || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == recipients
  }

  def "GetRecipient interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def recipient = new Recipient()
    Mockito.doReturn(new AccessorResponse<Recipient>().withResult(recipient)).when(recipientGateway).get("recipient_id")

    when:
    def response = subject.getRecipient("recipient_id")

    then:
    verify(recipientGateway).get("recipient_id") || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == recipient
  }

  def "DeleteRecipient interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    Mockito.doReturn(new AccessorResponse<Void>()).when(recipientGateway).delete("recipient_id")

    when:
    def response = subject.deleteRecipient("recipient_id")

    then:
    verify(recipientGateway).delete("recipient_id") || true
    noExceptionThrown()
    response.getStatusCode() == HttpStatus.NO_CONTENT
  }

  def "CreateRecipientPayoutMethod interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payoutMethod = new PayoutMethod()
    Mockito.doReturn(new AccessorResponse<PayoutMethod>().withResult(payoutMethod)).when(payoutMethodGateway).create("recipient_id", payoutMethod)

    when:
    def response = subject.createPayoutMethod("recipient_id", payoutMethod)

    then:
    verify(payoutMethodGateway).create("recipient_id", payoutMethod) || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == payoutMethod
  }

  def "ListRecipientPayoutMethods interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payoutMethods = new MdxList<PayoutMethod>()
    Mockito.doReturn(new AccessorResponse<MdxList<PayoutMethod>>().withResult(payoutMethods)).when(payoutMethodGateway).list("recipient_id")

    when:
    def response = subject.listPayoutMethods("recipient_id")

    then:
    verify(payoutMethodGateway).list("recipient_id") || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == payoutMethods
  }

  def "GetRecipientPayoutMethod interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payoutMethod = new PayoutMethod()
    Mockito.doReturn(new AccessorResponse<PayoutMethod>().withResult(payoutMethod)).when(payoutMethodGateway).get("recipient_id", "method_id")

    when:
    def response = subject.getPayoutMethod("recipient_id", "method_id")

    then:
    verify(payoutMethodGateway).get("recipient_id", "method_id") || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == payoutMethod
  }

  def "UpdateRecipientPayoutMethods interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def payoutMethod = new PayoutMethod()
    Mockito.doReturn(new AccessorResponse<PayoutMethod>().withResult(payoutMethod)).when(payoutMethodGateway).update("recipient_id", "method_id", payoutMethod)

    when:
    def response = subject.updatePayoutMethod("recipient_id", "method_id", payoutMethod)

    then:
    verify(payoutMethodGateway).update("recipient_id", "method_id", payoutMethod) || true
    response.getStatusCode() == HttpStatus.OK
    response.getBody().getWrapped()
    response.getBody() == payoutMethod
  }
}
