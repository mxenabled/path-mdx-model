package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.cross_account_transfer.DestinationAccount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class CrossAccountTransferDestinationController extends BaseController {

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/destinations", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<DestinationAccount>> list() {
    AccessorResponse<MdxList<DestinationAccount>> response = gateway().crossAccount().destination().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/destinations/{id}", method = RequestMethod.GET)
  public final ResponseEntity<DestinationAccount> get(@PathVariable("id") String destinationAccountID) {
    AccessorResponse<DestinationAccount> response = gateway().crossAccount().destination().get(destinationAccountID);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/destinations", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<DestinationAccount> create(@RequestBody DestinationAccount destinationAccount) {
    AccessorResponse<DestinationAccount> response = gateway().crossAccount().destination().create(destinationAccount);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/destinations/{id}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<DestinationAccount> update(@PathVariable("id") String destinationAccountID, @RequestBody DestinationAccount destinationAccount) {
    AccessorResponse<DestinationAccount> response = gateway().crossAccount().destination().update(destinationAccountID, destinationAccount);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/destinations/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<Void> delete(@PathVariable("id") String destinationAccountID) {
    AccessorResponse<Void> response = gateway().crossAccount().destination().delete(destinationAccountID);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }
}
