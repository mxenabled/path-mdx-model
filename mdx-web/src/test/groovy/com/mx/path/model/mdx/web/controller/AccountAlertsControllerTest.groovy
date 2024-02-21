package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.account.AccountAlertGateway
import com.mx.path.gateway.api.account.AccountGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.account.alerts.AccountAlert
import com.mx.path.model.mdx.model.account.alerts.DeliveryMethod

import org.springframework.http.HttpStatus

import spock.lang.Specification

class AccountAlertsControllerTest extends Specification {
  AccountAlertsController subject
  Gateway gateway
  AccountAlertGateway accountAlertGateway

  void setup() {
    subject = new AccountAlertsController()
    accountAlertGateway = spy(AccountAlertGateway.builder().build())

    gateway = Gateway.builder()
        .accounts(AccountGateway.builder()
        .accountAlerts(accountAlertGateway)
        .build())
        .build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "getAlert interacts with gateway"() {
    given:
    def accountId = "account-id"
    def alertId = "alert-id"
    def accountAlert = new AccountAlert()

    when:
    doReturn(new AccessorResponse<AccountAlert>().withResult(accountAlert)).when(accountAlertGateway).get(accountId, alertId)
    def response = subject.getAlert(accountId, alertId)

    then:
    verify(accountAlertGateway).get(accountId, alertId) || true
    response.body == accountAlert
  }

  def "getAlertList interacts with gateway"() {
    given:
    def accountId = "account-id"
    def alerts = new MdxList<AccountAlert>()
    alerts.add(new AccountAlert())

    when:
    doReturn(new AccessorResponse<MdxList<AccountAlert>>().withResult(alerts)).when(accountAlertGateway).list(accountId)
    def response = subject.getAlertList(accountId)

    then:
    verify(accountAlertGateway).list(accountId) || true
    response.body == alerts
  }

  def "createAlert interacts with gateway"() {
    given:
    def accountId = "account-id"
    def accountAlert = new AccountAlert()

    when:
    doReturn(new AccessorResponse<AccountAlert>().withResult(accountAlert)).when(accountAlertGateway).create(accountId, accountAlert)
    def response = subject.createAlert(accountId, accountAlert)

    then:
    verify(accountAlertGateway).create(accountId, accountAlert) || true
    response.body == accountAlert
  }

  def "updateAlert interacts with gateway"() {
    given:
    def accountId = "account-id"
    def accountAlert = new AccountAlert()

    when:
    doReturn(new AccessorResponse<AccountAlert>().withResult(accountAlert)).when(accountAlertGateway).update(accountId, accountAlert)
    def response = subject.updateAlert(accountId, accountAlert)

    then:
    verify(accountAlertGateway).update(accountId, accountAlert) || true
    response.body == accountAlert
  }

  def "deleteAlert interacts with gateway"() {
    given:
    def accountId = "account-id"
    def alertId = "alert-id"

    when:
    doReturn(new AccessorResponse<Void>()).when(accountAlertGateway).delete(accountId, alertId)
    def response = subject.deleteAlert(accountId, alertId)

    then:
    verify(accountAlertGateway).delete(accountId, alertId) || true
    HttpStatus.NO_CONTENT == response.statusCode
  }

  def "getDeliveryMethods interacts with gateway"() {
    given:
    def accountId = "account-id"
    def alertId = "alert-id"
    def deliveryMethods = new MdxList<DeliveryMethod>()
    deliveryMethods.add(new DeliveryMethod())

    when:
    doReturn(new AccessorResponse<MdxList<DeliveryMethod>>().withResult(deliveryMethods)).when(accountAlertGateway).deliveryMethods(accountId, alertId)
    def response = subject.getDeliveryMethods(accountId, alertId)

    then:
    verify(accountAlertGateway).deliveryMethods(accountId, alertId) || true
    response.body == deliveryMethods
  }

  def "getAccounts interacts with gateway"() {
    given:
    def accounts = new MdxList<Account>()
    accounts.add(new Account())

    when:
    doReturn(new AccessorResponse<MdxList<Account>>().withResult(accounts)).when(accountAlertGateway).accounts()
    def response = subject.getAccounts()

    then:
    verify(accountAlertGateway).accounts() || true
    response.body == accounts
  }
}
