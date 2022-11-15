package com.mx.web.mdx.controllers;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.AccountType;
import com.mx.models.account.Account;
import com.mx.models.cross_account_transfer.CrossAccountTransfer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class CrossAccountTransfersController extends BaseController {
  @RequestMapping(value = "/users/{userId}/cross_account_transfers", method = RequestMethod.POST)
  public final ResponseEntity<CrossAccountTransfer> createCrossAccountTransfer(@RequestBody CrossAccountTransfer crossAccountTransfer) {
    AccessorResponse<CrossAccountTransfer> response = gateway().crossAccount().create(crossAccountTransfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> deleteCrossAccountTransfer(@PathVariable("id") String transferId) {
    AccessorResponse<Void> response = gateway().crossAccount().delete(transferId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/{id}", method = RequestMethod.GET)
  public final ResponseEntity<CrossAccountTransfer> getCrossAccountTransfer(@PathVariable("id") String id) {
    AccessorResponse<CrossAccountTransfer> response = gateway().crossAccount().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<CrossAccountTransfer>> listCrossAccountTransfers() {
    AccessorResponse<MdxList<CrossAccountTransfer>> response = gateway().crossAccount().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @Deprecated
  @RequestMapping(value = "/account_types/cross_account_transfer", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<AccountType>> listCrossAccountTransferAccountTypesOldPath() {
    AccessorResponse<MdxList<AccountType>> response = gateway().crossAccount().accountTypes();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/cross_account_transfers/account_types", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<AccountType>> listCrossAccountTransferAccountTypes() {
    AccessorResponse<MdxList<AccountType>> response = gateway().crossAccount().accountTypes();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/cross_account_transfer_from", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Account>> listCrossAccountTransferFromAccounts() {
    AccessorResponse<MdxList<Account>> response = gateway().crossAccount().accounts();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/{id}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<CrossAccountTransfer> updateCrossAccountTransfer(@PathVariable("id") String transferId, @RequestBody CrossAccountTransfer crossAccountTransfer) {
    AccessorResponse<CrossAccountTransfer> response = gateway().crossAccount().update(transferId, crossAccountTransfer);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
