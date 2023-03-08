package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.dispute.DisputedTransaction
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.DisputeGateway
import com.mx.path.gateway.api.account.DisputedTransactionGateway
import com.mx.path.gateway.api.account.TransactionGateway
import com.mx.path.model.mdx.web.controller.BaseController
import com.mx.path.model.mdx.web.controller.DisputedTransactionsController

import org.springframework.http.HttpStatus

import spock.lang.Specification

class DisputedTransactionsControllerTest extends Specification {

  AccountGateway accountGateway
  DisputeGateway disputeGateway
  DisputedTransactionGateway disputedTransactionGateway
  Gateway gateway
  DisputedTransactionsController subject
  TransactionGateway transactionGateway

  def setup() {
    disputedTransactionGateway = spy(DisputedTransactionGateway.builder().build())
    disputeGateway = spy(DisputeGateway.builder().disputedTransactions(disputedTransactionGateway).build())
    transactionGateway = spy(TransactionGateway.builder().disputes(disputeGateway).build())
    accountGateway = spy(AccountGateway.builder().transactions(transactionGateway).build())
    gateway = Gateway.builder().accounts(accountGateway).build()
    subject = new DisputedTransactionsController()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "createDisputedTransaction interacts with gateway"() {
    given:
    def disputeId = "4321"
    def disputedTransaction = new DisputedTransaction()
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<DisputedTransaction>().withResult(disputedTransaction)).when(disputedTransactionGateway).create(disputeId, disputedTransaction)

    when:
    def response = subject.createDisputedTransaction(disputeId, disputedTransaction)

    then:
    verify(disputeGateway).disputedTransactions() || true
    verify(disputedTransactionGateway).create(disputeId, disputedTransaction) || true
    response.body == disputedTransaction
    response.statusCode == HttpStatus.OK
  }

  def "deleteDisputedTransaction interacts with gateway"() {
    given:
    def disputeId = "4321"
    def disputedTransactionId = "1234"
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<Void>()).when(disputedTransactionGateway).delete(disputeId, disputedTransactionId)

    when:
    def response = subject.deleteDisputedTransaction(disputeId, disputedTransactionId)

    then:
    verify(disputeGateway).disputedTransactions() || true
    verify(disputedTransactionGateway).delete(disputeId, disputedTransactionId) || true
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "getDisputedTransaction interacts with gateway"() {
    given:
    def disputedTransaction = new DisputedTransaction()
    def disputeId = "4321"
    def disputedTransactionId = "1234"
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<DisputedTransaction>().withResult(disputedTransaction)).when(disputedTransactionGateway).get(disputeId, disputedTransactionId)

    when:
    def response = subject.getDisputedTransaction(disputeId, disputedTransactionId)

    then:
    verify(disputeGateway).disputedTransactions() || true
    verify(disputedTransactionGateway).get(disputeId, disputedTransactionId) || true
    response.body == disputedTransaction
    response.statusCode == HttpStatus.OK
  }

  def "listDisputedTransactions interacts with gateway"() {
    given:
    def disputeId = "4321"
    def disputedTransactions = new MdxList<DisputedTransaction>().tap {add(new DisputedTransaction()) }
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<MdxList<DisputedTransaction>>().withResult(disputedTransactions)).when(disputedTransactionGateway).list(disputeId)

    when:
    def response = subject.listDisputedTransactions(disputeId)

    then:
    verify(disputeGateway).disputedTransactions() || true
    verify(disputedTransactionGateway).list(disputeId) || true
    response.body == disputedTransactions
    response.statusCode == HttpStatus.OK
  }

  def "updateDisputedTransaction interacts with gateway"() {
    given:
    def disputeId = "4321"
    def disputedTransactionId = "1234"
    def disputedTransaction = new DisputedTransaction()
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<DisputedTransaction>().withResult(disputedTransaction)).when(disputedTransactionGateway).update(disputeId, disputedTransactionId, disputedTransaction)

    when:
    def response = subject.updateDisputedTransaction(disputeId, disputedTransactionId, disputedTransaction)

    then:
    verify(disputeGateway).disputedTransactions() || true
    verify(disputedTransactionGateway).update(disputeId, disputedTransactionId, disputedTransaction) || true
    response.body == disputedTransaction
    response.statusCode == HttpStatus.OK
  }
}
