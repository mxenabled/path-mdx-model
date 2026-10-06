package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.cross_account_transfer.CrossAccountRecurringTransfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class CrossAccountRecurringTransferController extends BaseController {
  public CrossAccountRecurringTransferController() {
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/recurring_cross_account_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<CrossAccountRecurringTransfer>> getCrossAccountRecurringTransfers() {
    AccessorResponse<MdxList<CrossAccountRecurringTransfer>> response = gateway().crossAccount().crossAccountRecurring().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/recurring_cross_account_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<CrossAccountRecurringTransfer> getCrossAccountRecurringTransfer(@PathVariable("id") String recurringTransferId) {
    AccessorResponse<CrossAccountRecurringTransfer> response = gateway().crossAccount().crossAccountRecurring().get(recurringTransferId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/recurring_cross_account_transfers", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<CrossAccountRecurringTransfer> createRecurringTransfers(@RequestBody CrossAccountRecurringTransfer crossAccountRecurringTransfer) {
    AccessorResponse<CrossAccountRecurringTransfer> response = gateway().crossAccount().crossAccountRecurring().create(crossAccountRecurringTransfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/recurring_cross_account_transfers/{id}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<CrossAccountRecurringTransfer> updateCrossAccountRecurringTransfer(@PathVariable("id") String recurringTransferId, @RequestBody CrossAccountRecurringTransfer crossAccountRecurringTransfer) {
    AccessorResponse<CrossAccountRecurringTransfer> response = gateway().crossAccount().crossAccountRecurring().update(recurringTransferId, crossAccountRecurringTransfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/recurring_cross_account_transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> deleteCrossAccountRecurringTransfer(@PathVariable("id") String recurringTransferId) {
    AccessorResponse<Void> response = gateway().crossAccount().crossAccountRecurring().delete(recurringTransferId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/recurring_cross_account_transfers/{id}/skip_next", method = RequestMethod.PUT)
  public final ResponseEntity<?> skipCrossAccountRecurringTransfer(@PathVariable("id") String recurringTransferId) {
    AccessorResponse<CrossAccountRecurringTransfer> response = gateway().crossAccount().crossAccountRecurring().skipNext(recurringTransferId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }
}
