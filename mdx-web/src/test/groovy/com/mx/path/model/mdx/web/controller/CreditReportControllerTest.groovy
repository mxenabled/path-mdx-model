package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.credit_report.CreditReportGateway
import com.mx.path.gateway.api.credit_report.CreditReportSettingsGateway
import com.mx.path.gateway.api.credit_report.ScoreFactorGateway
import com.mx.path.model.mdx.model.credit_report.CreditReport
import com.mx.path.model.mdx.model.credit_report.CreditReportScoreFactor
import com.mx.path.model.mdx.model.credit_report.CreditReportSettings
import com.mx.path.testing.WithMockery

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class CreditReportControllerTest extends Specification implements WithMockery {
  CreditReportController subject

  Gateway gateway
  CreditReportGateway creditReportGateway
  CreditReportSettingsGateway creditReportSettingsGateway
  ScoreFactorGateway scoreFactorGateway

  def setup() {
    subject = new CreditReportController()
    creditReportSettingsGateway = spy(CreditReportSettingsGateway.builder().build())
    scoreFactorGateway = spy(ScoreFactorGateway.builder().build())
    creditReportGateway = spy(CreditReportGateway.builder().settings(creditReportSettingsGateway).scoreFactors(scoreFactorGateway).build())
    gateway = Gateway.builder().creditReports(creditReportGateway).build()
  }

  def cleanup() {
    CreditReportController.clearRepository()
  }

  def "getCreditReportSettings interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    CreditReportSettings settings = new CreditReportSettings();

    when:
    Mockito.doReturn(new AccessorResponse<CreditReportSettings>().withResult(settings)).when(creditReportSettingsGateway).get()
    def response = subject.getCreditReportSettings()

    then:
    response.getBody() == settings
  }

  def "subscribeCreditReport interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    CreditReportSettings settings = new CreditReportSettings();

    when:
    Mockito.doReturn(new AccessorResponse<CreditReportSettings>().withResult(settings)).when(creditReportGateway).subscribe()
    def response = subject.subscribeCreditReport()

    then:
    response.getBody() == settings
    response.getStatusCode() == HttpStatus.OK
  }

  def "unsubscribeCreditReport interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    CreditReportSettings settings = new CreditReportSettings();

    when:
    Mockito.doReturn(new AccessorResponse<CreditReportSettings>().withResult(settings)).when(creditReportGateway).unsubscribe()
    def response = subject.unsubscribeCreditReport()

    then:
    response.getBody() == settings
    response.getStatusCode() == HttpStatus.OK
  }

  def "getAllScoreFactors interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    MdxList<CreditReportScoreFactor> creditReportScoreFactors = new MdxList<>()
    CreditReportScoreFactor creditReportScoreFactor = new CreditReportScoreFactor()
    creditReportScoreFactors.add(creditReportScoreFactor)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<CreditReportScoreFactor>>().withResult(creditReportScoreFactors)).when(scoreFactorGateway).list()
    def response = subject.getScoreFactors()

    then:
    response.getBody() == creditReportScoreFactors
    response.getStatusCode() == HttpStatus.OK
  }

  def "getAllScoreFactor interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    CreditReportScoreFactor creditReportScoreFactor = new CreditReportScoreFactor()

    when:
    Mockito.doReturn(new AccessorResponse<CreditReportScoreFactor>().withResult(creditReportScoreFactor)).when(scoreFactorGateway).get("report-123", "factor-123")
    def response = subject.getScoreFactor("report-123", "factor-123")

    then:
    response.getBody() == creditReportScoreFactor
    response.getStatusCode() == HttpStatus.OK
  }

  def "getAllCreditReports interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    MdxList<CreditReport> creditReports = new MdxList<>()
    CreditReport creditReport = new CreditReport()
    creditReports.add(creditReport)

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<CreditReport>>().withResult(creditReports)).when(creditReportGateway).list()
    def response = subject.getCreditReports()

    then:
    response.getBody() == creditReports
    response.getStatusCode() == HttpStatus.OK
  }

  def "getAllCreditReport interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    CreditReport creditReport = new CreditReport()

    when:
    Mockito.doReturn(new AccessorResponse<CreditReport>().withResult(creditReport)).when(creditReportGateway).get("report-123")
    def response = subject.getCreditReport("report-123")

    then:
    response.getBody() == creditReport
    response.getStatusCode() == HttpStatus.OK
  }
}
