package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.external_transfer.RecurringExternalTransfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class RecurringExternalTransfersController extends BaseController {
  @RequestMapping(value = "/users/{userId}/recurring_external_transfers", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<RecurringExternalTransfer> create(@RequestBody RecurringExternalTransfer recurringExternalTransferRequest) {
    AccessorResponse<RecurringExternalTransfer> response = gateway().externalTransfers().recurring().create(recurringExternalTransferRequest);
    RecurringExternalTransfer result = response.getResult();
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), statusFor(result));
  }

  @RequestMapping(value = "/users/{userId}/recurring_external_transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> cancel(@PathVariable("id") String id) {
    AccessorResponse<Void> response = gateway().externalTransfers().recurring().cancel(id);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/recurring_external_transfers/frequencies", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Frequency>> frequencies() {
    AccessorResponse<MdxList<Frequency>> response = gateway().externalTransfers().recurring().frequencies().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_external_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<RecurringExternalTransfer> get(@PathVariable("id") String id) {
    AccessorResponse<RecurringExternalTransfer> response = gateway().externalTransfers().recurring().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_external_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<RecurringExternalTransfer>> list() {
    AccessorResponse<MdxList<RecurringExternalTransfer>> response = gateway().externalTransfers().recurring().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_external_transfers/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<RecurringExternalTransfer> update(@PathVariable("id") String id, @RequestBody RecurringExternalTransfer recurringExternalTransferRequest) {
    AccessorResponse<RecurringExternalTransfer> response = gateway().externalTransfers().recurring().update(id, recurringExternalTransferRequest);
    RecurringExternalTransfer result = response.getResult();
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), statusFor(result));
  }

  private static HttpStatus statusFor(RecurringExternalTransfer result) {
    if (result.getChallenges() != null && !result.getChallenges().isEmpty()) {
      return HttpStatus.ACCEPTED;
    }
    return HttpStatus.OK;
  }
}
