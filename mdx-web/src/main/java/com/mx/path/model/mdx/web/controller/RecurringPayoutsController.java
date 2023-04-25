package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.payout.RecurringPayout;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class RecurringPayoutsController extends BaseController {

  @RequestMapping(value = "/users/{user_id}/recurring_payouts", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<RecurringPayout> createRecurringPayout(@RequestBody RecurringPayout recurringPayoutRequest) {
    AccessorResponse<RecurringPayout> response = gateway().payouts().recurring().create(recurringPayoutRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/recurring_payouts/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> cancelRecurringPayout(@PathVariable("id") String recurringPayoutId) {
    AccessorResponse<Void> response = gateway().payouts().recurring().cancel(recurringPayoutId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{user_id}/recurring_payouts", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<RecurringPayout>> listRecurringPayouts() {
    AccessorResponse<MdxList<RecurringPayout>> response = gateway().payouts().recurring().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/recurring_payouts/{id}", method = RequestMethod.GET)
  public final ResponseEntity<RecurringPayout> getRecurringPayout(@PathVariable("id") String recurringPayoutId) {
    AccessorResponse<RecurringPayout> response = gateway().payouts().recurring().get(recurringPayoutId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/recurring_payouts/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<RecurringPayout> updateRecurringPayout(@PathVariable("id") String recurringPayoutId, @RequestBody RecurringPayout recurringPayout) {
    recurringPayout.setId(recurringPayoutId);
    AccessorResponse<RecurringPayout> response = gateway().payouts().recurring().update(recurringPayoutId, recurringPayout);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/recurring_payouts/{id}/skip_next", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<?> skipNextRecurringPayout(@PathVariable("id") String recurringPayoutId) {
    AccessorResponse<RecurringPayout> response = gateway().payouts().recurring().skipNext(recurringPayoutId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/frequencies/recurring_payout", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Frequency>> listRecurringFrequencies() {
    AccessorResponse<MdxList<Frequency>> response = gateway().payouts().recurring().frequencies();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
