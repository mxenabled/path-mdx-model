package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.p2p_transfer.Recipient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class P2PTransferRecipientsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/p2p_transfers/recipients", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Recipient> create(@RequestBody Recipient recipientRequest) {
    AccessorResponse<Recipient> response = gateway().p2pTransfers().recipients().create(recipientRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/p2p_transfers/recipients/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> delete(@PathVariable("id") String recipientId) {
    AccessorResponse<Void> response = gateway().p2pTransfers().recipients().delete(recipientId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/p2p_transfers/recipients/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Recipient> get(@PathVariable("id") String recipientId) {
    AccessorResponse<Recipient> response = gateway().p2pTransfers().recipients().get(recipientId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/p2p_transfers/recipients", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Recipient>> list() {
    AccessorResponse<MdxList<Recipient>> response = gateway().p2pTransfers().recipients().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/p2p_transfers/recipients/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Recipient> update(@PathVariable("id") String recipientId, @RequestBody Recipient recipientRequest) {
    AccessorResponse<Recipient> response = gateway().p2pTransfers().recipients().update(recipientId, recipientRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
