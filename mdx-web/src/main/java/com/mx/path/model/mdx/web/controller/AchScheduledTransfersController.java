package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.ach_transfer.AchScheduledTransfer;
import com.mx.path.model.mdx.model.ach_transfer.options.AchScheduledTransferListOptions;
import com.mx.path.model.mdx.web.model.ach_transfer.AchScheduledTransferListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AchScheduledTransfersController extends BaseController {

  public AchScheduledTransfersController() {
  }

  @RequestMapping(value = "/users/{userId}/ach_scheduled_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<AchScheduledTransfer>> list(AchScheduledTransferListQueryParameters queryParameters) {
    AchScheduledTransferListOptions options = new AchScheduledTransferListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    AccessorResponse<MdxList<AchScheduledTransfer>> response = gateway().achTransfers().scheduled().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_scheduled_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<AchScheduledTransfer> get(@PathVariable("id") String id) {
    AccessorResponse<AchScheduledTransfer> response = gateway().achTransfers().scheduled().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_scheduled_transfers", method = RequestMethod.POST)
  public final ResponseEntity<AchScheduledTransfer> create(@RequestBody AchScheduledTransfer achTransfer) {
    AccessorResponse<AchScheduledTransfer> response = gateway().achTransfers().scheduled().create(achTransfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_scheduled_transfers/{id}", method = RequestMethod.PUT)
  public final ResponseEntity<AchScheduledTransfer> update(@PathVariable("id") String id, @RequestBody AchScheduledTransfer achTransfer) {
    achTransfer.setId(id);
    AccessorResponse<AchScheduledTransfer> response = gateway().achTransfers().scheduled().update(id, achTransfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_scheduled_transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<Void> cancel(@PathVariable("id") String id) {
    AccessorResponse<Void> response = gateway().achTransfers().scheduled().cancel(id);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }
}
