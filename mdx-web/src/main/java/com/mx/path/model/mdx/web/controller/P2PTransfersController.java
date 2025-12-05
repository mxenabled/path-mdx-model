package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.p2p_transfer.P2PTransfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class P2PTransfersController extends BaseController {
  @RequestMapping(value = "/users/{userId}/p2p_transfers", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<P2PTransfer> create(@RequestBody P2PTransfer p2pTransferRequest) {
    AccessorResponse<P2PTransfer> response = gateway().p2pTransfers().create(p2pTransferRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/p2p_transfers/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> delete(@PathVariable("id") String p2pTransferId) {
    AccessorResponse<Void> response = gateway().p2pTransfers().delete(p2pTransferId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/p2p_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<P2PTransfer> get(@PathVariable("id") String p2pTransferId) {
    AccessorResponse<P2PTransfer> response = gateway().p2pTransfers().get(p2pTransferId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/p2p_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<P2PTransfer>> list() {
    AccessorResponse<MdxList<P2PTransfer>> response = gateway().p2pTransfers().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/p2p_transfers/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<P2PTransfer> update(@PathVariable("id") String p2pTransferId, @RequestBody P2PTransfer p2pTransferRequest) {
    AccessorResponse<P2PTransfer> response = gateway().p2pTransfers().update(p2pTransferId, p2pTransferRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
