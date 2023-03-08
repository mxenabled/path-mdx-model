package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.dispute.DisputedTransaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class DisputedTransactionsController extends BaseController {

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{dispute_id}/disputed_transactions", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<DisputedTransaction> createDisputedTransaction(@PathVariable("dispute_id") String disputeId, @RequestBody DisputedTransaction disputedTransactionRequest) {
    disputedTransactionRequest.setDisputeId(disputeId);
    AccessorResponse<DisputedTransaction> response = gateway().accounts().transactions().disputes().disputedTransactions().create(disputeId, disputedTransactionRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{dispute_id}/disputed_transactions/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<MdxList<DisputedTransaction>> deleteDisputedTransaction(@PathVariable("dispute_id") String disputeId, @PathVariable("id") String disputedTransactionId) {
    AccessorResponse<Void> response = gateway().accounts().transactions().disputes().disputedTransactions().delete(disputeId, disputedTransactionId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{dispute_id}/disputed_transactions/{id}", method = RequestMethod.GET)
  public final ResponseEntity<DisputedTransaction> getDisputedTransaction(@PathVariable("dispute_id") String disputeId, @PathVariable("id") String disputedTransactionId) {
    AccessorResponse<DisputedTransaction> response = gateway().accounts().transactions().disputes().disputedTransactions().get(disputeId, disputedTransactionId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{dispute_id}/disputed_transactions", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<DisputedTransaction>> listDisputedTransactions(@PathVariable("dispute_id") String disputeId) {
    AccessorResponse<MdxList<DisputedTransaction>> response = gateway().accounts().transactions().disputes().disputedTransactions().list(disputeId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{dispute_id}/disputed_transactions/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<DisputedTransaction> updateDisputedTransaction(@PathVariable("dispute_id") String disputeId, @PathVariable("id") String disputedTransactionId, @RequestBody DisputedTransaction disputedTransaction) {
    disputedTransaction.setDisputeId(disputeId);
    disputedTransaction.setId(disputedTransactionId);
    AccessorResponse<DisputedTransaction> response = gateway().accounts().transactions().disputes().disputedTransactions().update(disputeId, disputedTransactionId, disputedTransaction);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
