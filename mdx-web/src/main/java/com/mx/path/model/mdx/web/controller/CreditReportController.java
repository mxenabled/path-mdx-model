package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.credit_report.CreditReport;
import com.mx.models.credit_report.CreditReportScoreFactor;
import com.mx.models.credit_report.CreditReportSettings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class CreditReportController extends BaseController {

  public CreditReportController() {
  }

  @RequestMapping(value = "/users/{userId}/credit_reports/settings", method = RequestMethod.GET)
  public final ResponseEntity<CreditReportSettings> getCreditReportSettings() {
    AccessorResponse<CreditReportSettings> response = gateway().creditReports().settings().get();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/credit_reports/settings/subscribe", method = RequestMethod.PUT)
  public final ResponseEntity<CreditReportSettings> subscribeCreditReport() {
    AccessorResponse<CreditReportSettings> response = gateway().creditReports().subscribe();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/credit_reports/settings/unsubscribe", method = RequestMethod.PUT)
  public final ResponseEntity<CreditReportSettings> unsubscribeCreditReport() {
    AccessorResponse<CreditReportSettings> response = gateway().creditReports().unsubscribe();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/credit_reports/{reportId}/score_factors", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<CreditReportScoreFactor>> getScoreFactors(@PathVariable("reportId") String reportId) {
    AccessorResponse<MdxList<CreditReportScoreFactor>> response = gateway().creditReports().scoreFactors().list(reportId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/credit_reports/{reportId}/score_factors/{factorId}", method = RequestMethod.GET)
  public final ResponseEntity<CreditReportScoreFactor> getScoreFactor(@PathVariable("reportId") String reportId, @PathVariable("factorId") String factorId) {
    AccessorResponse<CreditReportScoreFactor> response = gateway().creditReports().scoreFactors().get(reportId, factorId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/credit_reports", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<CreditReport>> getCreditReports() {
    AccessorResponse<MdxList<CreditReport>> response = gateway().creditReports().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/credit_reports/{reportId}", method = RequestMethod.GET)
  public final ResponseEntity<CreditReport> getCreditReport(@PathVariable("reportId") String reportId) {
    AccessorResponse<CreditReport> response = gateway().creditReports().get(reportId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
