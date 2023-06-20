package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.gateway.api.account.DisputeGateway
import com.mx.path.gateway.api.account.TransactionGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.dispute.Dispute

import org.springframework.http.HttpStatus

import spock.lang.Specification

class DisputesControllerTest extends Specification {
  DisputesController subject

  Gateway gateway
  AccountGateway accountGateway
  DisputeGateway disputeGateway
  TransactionGateway transactionGateway

  def setup() {
    disputeGateway = spy(DisputeGateway.builder().build())
    transactionGateway = spy(TransactionGateway.builder().disputes(disputeGateway).build())
    accountGateway = spy(AccountGateway.builder().transactions(transactionGateway).build())
    gateway = Gateway.builder().accounts(accountGateway).build()
    subject = new DisputesController()
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "cancelDispute interacts with gateway"() {
    given:
    def disputeId = "1234"
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<Void>()).when(disputeGateway).cancel(disputeId)

    when:
    def response = subject.cancelDispute(disputeId)

    then:
    verify(disputeGateway).cancel(disputeId) || true
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "getDispute interacts with gateway"() {
    given:
    def dispute = new Dispute()
    def disputeId = "1234"
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<Dispute>().withResult(dispute)).when(disputeGateway).get(disputeId)

    when:
    def response = subject.getDispute(disputeId)

    then:
    verify(disputeGateway).get(disputeId) || true
    response.body == dispute
    response.statusCode == HttpStatus.OK
  }

  def "listDisputes interacts with gateway"() {
    given:
    def disputes = new MdxList<Dispute>().tap {add(new Dispute()) }
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<MdxList<Dispute>>().withResult(disputes)).when(disputeGateway).list()

    when:
    def response = subject.listDisputes()

    then:
    verify(disputeGateway).list() || true
    response.body == disputes
    response.statusCode == HttpStatus.OK
  }

  def "startDisputet interacts with gateway"() {
    given:
    def dispute = new Dispute()
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<Dispute>().withResult(dispute)).when(disputeGateway).start(dispute)

    when:
    def response = subject.startDispute(dispute)

    then:
    verify(disputeGateway).start(dispute) || true
    response.body == dispute
    response.statusCode == HttpStatus.OK
  }

  def "submitDispute interacts with gateway"() {
    given:
    def disputeId = "1234"
    def dispute = new Dispute()
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<Dispute>().withResult(dispute)).when(disputeGateway).submit(disputeId)

    when:
    def response = subject.submitDispute(disputeId)

    then:
    verify(disputeGateway).submit(disputeId) || true
    response.body == dispute
    response.statusCode == HttpStatus.OK
  }

  def "updateDispute interacts with gateway"() {
    given:
    def disputeId = "1234"
    def dispute = new Dispute()
    BaseController.setGateway(gateway)
    doReturn(new AccessorResponse<Dispute>().withResult(dispute)).when(disputeGateway).update(disputeId, dispute)

    when:
    def response = subject.updateDispute(disputeId, dispute)

    then:
    verify(disputeGateway).update(disputeId, dispute) || true
    response.body == dispute
    response.statusCode == HttpStatus.OK
  }
}
