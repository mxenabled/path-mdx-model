package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.dispute.Dispute;
import com.mx.path.model.mdx.model.dispute.DisputedTransaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class DisputesController extends BaseController {

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<MdxList<DisputedTransaction>> cancelDispute(@PathVariable("id") String disputeId) {
    AccessorResponse<Void> response = gateway().accounts().transactions().disputes().cancel(disputeId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Dispute> getDispute(@PathVariable("id") String disputeId) {
    AccessorResponse<Dispute> response = gateway().accounts().transactions().disputes().get(disputeId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Dispute>> listDisputes() {
    AccessorResponse<MdxList<Dispute>> response = gateway().accounts().transactions().disputes().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Dispute> startDispute(@RequestBody Dispute disputeRequest) {
    AccessorResponse<Dispute> response = gateway().accounts().transactions().disputes().start(disputeRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{id}/submit", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Dispute> submitDispute(@PathVariable("id") String disputeId) {
    AccessorResponse<Dispute> response = gateway().accounts().transactions().disputes().submit(disputeId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/disputes/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Dispute> updateDispute(@PathVariable("id") String disputeId, @RequestBody Dispute dispute) {
    dispute.setId(disputeId);
    AccessorResponse<Dispute> response = gateway().accounts().transactions().disputes().update(disputeId, dispute);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
