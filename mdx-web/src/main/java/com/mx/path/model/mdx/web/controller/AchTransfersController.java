package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.ach_transfer.AchTransfer;
import com.mx.path.model.mdx.model.ach_transfer.options.AchTransferListOptions;
import com.mx.path.model.mdx.web.model.ach_transfer.AchTransferListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AchTransfersController extends BaseController {

  public AchTransfersController() {
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<AchTransfer>> list(AchTransferListQueryParameters queryParameters) {
    AchTransferListOptions options = new AchTransferListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    AccessorResponse<MdxList<AchTransfer>> response = gateway().achTransfers().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<AchTransfer> get(@PathVariable("id") String id) {
    AccessorResponse<AchTransfer> response = gateway().achTransfers().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers", method = RequestMethod.POST)
  public final ResponseEntity<AchTransfer> create(@RequestBody AchTransfer achTransfer) {
    AccessorResponse<AchTransfer> response = gateway().achTransfers().create(achTransfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers/{id}", method = RequestMethod.PUT)
  public final ResponseEntity<AchTransfer> update(@PathVariable("id") String id, @RequestBody AchTransfer achTransfer) {
    achTransfer.setId(id);
    AccessorResponse<AchTransfer> response = gateway().achTransfers().update(id, achTransfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<Void> cancel(@PathVariable("id") String id) {
    AccessorResponse<Void> response = gateway().achTransfers().cancel(id);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }
}
