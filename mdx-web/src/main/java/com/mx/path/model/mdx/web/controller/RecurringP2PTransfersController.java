package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.p2p_transfer.RecurringP2PTransfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class RecurringP2PTransfersController extends BaseController {
  @RequestMapping(value = "/users/{userId}/recurring_p2p_transfers", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<RecurringP2PTransfer> create(@RequestBody RecurringP2PTransfer p2pTransferRequest) {
    AccessorResponse<RecurringP2PTransfer> response = gateway().p2pTransfers().recurring().create(p2pTransferRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_p2p_transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> cancel(@PathVariable("id") String p2pTransferId) {
    AccessorResponse<Void> response = gateway().p2pTransfers().recurring().cancel(p2pTransferId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/recurring_p2p_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<RecurringP2PTransfer> get(@PathVariable("id") String p2pTransferId) {
    AccessorResponse<RecurringP2PTransfer> response = gateway().p2pTransfers().recurring().get(p2pTransferId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_p2p_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<RecurringP2PTransfer>> list() {
    AccessorResponse<MdxList<RecurringP2PTransfer>> response = gateway().p2pTransfers().recurring().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/recurring_p2p_transfers/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<RecurringP2PTransfer> update(@PathVariable("id") String p2pTransferId, @RequestBody RecurringP2PTransfer p2pTransferRequest) {
    AccessorResponse<RecurringP2PTransfer> response = gateway().p2pTransfers().recurring().update(p2pTransferId, p2pTransferRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
