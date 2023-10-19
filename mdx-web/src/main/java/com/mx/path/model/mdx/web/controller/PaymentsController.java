package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.payment.Enrollment;
import com.mx.path.model.mdx.model.payment.Payment;
import com.mx.path.model.mdx.model.payment.Settings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}/users/{user_id}", produces = BaseController.MDX_MEDIA)
public class PaymentsController extends BaseController {

  @RequestMapping(value = "/payments/enrollment", method = RequestMethod.GET, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Enrollment> getPaymentEnrollment() {
    AccessorResponse<Enrollment> response = gateway().payments().enrollment();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payments/enrollment", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Enrollment> setPaymentEnrollment(@RequestBody Enrollment enrollmentRequest) {
    AccessorResponse<Enrollment> response = gateway().payments().updateEnrollment(enrollmentRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payments/settings", method = RequestMethod.GET, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Settings> getPaymentSettings() {
    AccessorResponse<Settings> response = gateway().payments().settings();
    Settings result = response.getResult();
    // Return 202 returning challenge questions
    if (result != null && result.getChallenges() != null && result.getChallenges().size() > 0) {
      return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payments/settings", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Settings> setPaymentSettings(@RequestBody Settings settingsRequest) {
    AccessorResponse<Settings> response = gateway().payments().updateSettings(settingsRequest);
    Settings result = response.getResult();
    // Return 202 returning challenge questions
    if (result != null && result.getChallenges() != null && result.getChallenges().size() > 0) {
      return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payments", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Payment> createPayment(@RequestBody Payment paymentRequest) {
    AccessorResponse<Payment> response = gateway().payments().create(paymentRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payments", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Payment>> getPaymentList() {
    AccessorResponse<MdxList<Payment>> response = gateway().payments().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payments/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Payment> getPayment(@PathVariable("id") String paymentId) {
    AccessorResponse<Payment> response = gateway().payments().get(paymentId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payments/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> cancelPayment(@PathVariable("id") String paymentId) {
    AccessorResponse<Void> response = gateway().payments().cancel(paymentId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/payments/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Payment> updatePayment(@PathVariable("id") String paymentId, @RequestBody Payment paymentRequest) {
    paymentRequest.setId(paymentId);
    AccessorResponse<Payment> response = gateway().payments().update(paymentId, paymentRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/accounts/payment", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Account>> getAccountsUsedForPayments() {
    AccessorResponse<MdxList<Account>> response = gateway().payments().accounts();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
