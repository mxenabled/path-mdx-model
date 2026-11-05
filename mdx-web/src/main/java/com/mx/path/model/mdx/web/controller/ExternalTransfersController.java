package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.external_transfer.ExternalTransfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class ExternalTransfersController extends BaseController {
  @RequestMapping(value = "/users/{userId}/external_transfers", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<ExternalTransfer> create(@RequestBody ExternalTransfer externalTransferRequest) {
    AccessorResponse<ExternalTransfer> response = gateway().externalTransfers().create(externalTransferRequest);
    ExternalTransfer result = response.getResult();
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), statusFor(result));
  }

  @RequestMapping(value = "/users/{userId}/external_transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> cancel(@PathVariable("id") String id) {
    AccessorResponse<Void> response = gateway().externalTransfers().cancel(id);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/external_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<ExternalTransfer> get(@PathVariable("id") String id) {
    AccessorResponse<ExternalTransfer> response = gateway().externalTransfers().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/external_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<ExternalTransfer>> list() {
    AccessorResponse<MdxList<ExternalTransfer>> response = gateway().externalTransfers().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/external_transfers/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<ExternalTransfer> update(@PathVariable("id") String id, @RequestBody ExternalTransfer externalTransferRequest) {
    AccessorResponse<ExternalTransfer> response = gateway().externalTransfers().update(id, externalTransferRequest);
    ExternalTransfer result = response.getResult();
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), statusFor(result));
  }

  private static HttpStatus statusFor(ExternalTransfer result) {
    if (result.getChallenges() != null && !result.getChallenges().isEmpty()) {
      return HttpStatus.ACCEPTED;
    }
    return HttpStatus.OK;
  }
}
