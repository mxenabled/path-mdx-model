package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.payment.RecurringPayment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class RecurringPaymentsController extends BaseController {

  @RequestMapping(value = "/recurring_payments/frequencies", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Frequency>> getRecurringPaymentFrequencies() {
    AccessorResponse<MdxList<Frequency>> response = gateway().payments().recurring().frequencies();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_payments", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<RecurringPayment>> getRecurringPayments() {
    AccessorResponse<MdxList<RecurringPayment>> response = gateway().payments().recurring().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_payments", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<RecurringPayment> createRecurringPayment(@RequestBody RecurringPayment recurringPaymentRequest) {
    AccessorResponse<RecurringPayment> response = gateway().payments().recurring().create(recurringPaymentRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_payments/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> cancelRecurringPayment(@PathVariable("id") String recurringPaymentId) {
    AccessorResponse<Void> response = gateway().payments().recurring().cancel(recurringPaymentId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/recurring_payments/{id}", method = RequestMethod.GET)
  public final ResponseEntity<RecurringPayment> getRecurringPayment(@PathVariable("id") String recurringPaymentId) {
    AccessorResponse<RecurringPayment> response = gateway().payments().recurring().get(recurringPaymentId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_payments/{id}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<RecurringPayment> updateRecurringPayment(@PathVariable("id") String recurringPaymentId, @RequestBody RecurringPayment recurringPaymentRequest) {
    recurringPaymentRequest.setId(recurringPaymentId);
    AccessorResponse<RecurringPayment> response = gateway().payments().recurring().update(recurringPaymentId, recurringPaymentRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
