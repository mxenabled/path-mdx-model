package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.RecurringTransfer;
import com.mx.path.model.mdx.model.transfer.options.RecurringTransferListOptions;
import com.mx.path.model.mdx.web.model.transfer.RecurringTransferListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class RecurringTransfersController extends BaseController {

  @RequestMapping(value = "/users/{userId}/recurring_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<RecurringTransfer>> list(RecurringTransferListQueryParameters queryParameters) {
    RecurringTransferListOptions options = new RecurringTransferListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    AccessorResponse<MdxList<RecurringTransfer>> response = gateway().transfers().recurring().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_transfers", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<RecurringTransfer> postRecurringTransfers(@RequestBody RecurringTransfer recurringTransferRequest) {
    AccessorResponse<RecurringTransfer> response = gateway().transfers().recurring().create(recurringTransferRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> deleteRecurringTransfer(@PathVariable("id") String recurringTransferId) {
    AccessorResponse<Void> response = gateway().transfers().recurring().delete(recurringTransferId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/recurring_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<RecurringTransfer> getRecurringTransfer(@PathVariable("id") String recurringTransferId) {
    AccessorResponse<RecurringTransfer> response = gateway().transfers().recurring().get(recurringTransferId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_transfers/{id}/skip_next", method = RequestMethod.PUT)
  public final ResponseEntity<RecurringTransfer> skipNextRecurringTransfer(@PathVariable("id") String recurringTransferId) {
    AccessorResponse<RecurringTransfer> response = gateway().transfers().recurring().skipNext(recurringTransferId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_transfers/{id}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<RecurringTransfer> updateRecurringTransfer(@PathVariable("id") String recurringTransferId, @RequestBody RecurringTransfer recurringTransferRequest) {
    recurringTransferRequest.setId(recurringTransferId);
    AccessorResponse<RecurringTransfer> response = gateway().transfers().recurring().update(recurringTransferId, recurringTransferRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
