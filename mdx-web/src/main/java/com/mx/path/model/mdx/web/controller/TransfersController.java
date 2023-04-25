package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.Transfer;
import com.mx.path.model.mdx.model.transfer.options.TransferListOptions;
import com.mx.path.model.mdx.web.model.transfer.TransferListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class TransfersController extends BaseController {

  @RequestMapping(value = "/users/{userId}/transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Transfer>> list(TransferListQueryParameters queryParameters) {
    TransferListOptions options = new TransferListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    AccessorResponse<MdxList<Transfer>> response = gateway().transfers().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Transfer> getTransfer(@PathVariable("id") String transferId) {
    AccessorResponse<Transfer> response = gateway().transfers().get(transferId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/transfers", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<Transfer> postTransfers(@RequestBody Transfer transferRequest) {
    AccessorResponse<Transfer> response = gateway().transfers().create(transferRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/transfers/{id}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<Transfer> updateTransfer(@PathVariable("id") String transferId, @RequestBody Transfer transfer) {
    transfer.setId(transferId);
    AccessorResponse<Transfer> response = gateway().transfers().update(transferId, transfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> cancelTransfer(@PathVariable("id") String transferId) {
    AccessorResponse<Void> response = gateway().transfers().cancel(transferId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{user_id}/transfers/start", method = RequestMethod.POST)
  public final ResponseEntity<Transfer> start(@RequestBody Transfer transfer) {
    AccessorResponse<Transfer> response = gateway().transfers().start(transfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/transfers/{id}/finish", method = RequestMethod.PUT)
  public final ResponseEntity<Transfer> finish(@PathVariable("id") String transferId) {
    AccessorResponse<Transfer> response = gateway().transfers().finish(transferId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
