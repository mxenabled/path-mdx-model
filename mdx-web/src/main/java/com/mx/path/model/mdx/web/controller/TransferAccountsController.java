package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.account.Account;
import com.mx.models.transfer.options.AccountListOptions;
import com.mx.path.model.mdx.web.model.transfer.TransferAccountListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class TransferAccountsController extends BaseController {

  @RequestMapping(value = "/users/{userId}/accounts/transfer", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Account>> list(TransferAccountListQueryParameters queryParameters) {
    AccountListOptions options = new AccountListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    options.setAccountId(queryParameters.getAccount_id());
    options.setListType(queryParameters.getList_type());
    options.setFlow(queryParameters.getFlow());
    AccessorResponse<MdxList<Account>> response = gateway().transfers().accounts().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @Deprecated
  @RequestMapping(value = "/users/{userId}/accounts/transfer_from", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Account>> getAccountTransfersFrom() {
    AccessorResponse<MdxList<Account>> response = gateway().transfers().accounts().fromAccounts();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @Deprecated
  @RequestMapping(value = "/users/{userId}/accounts/{id}/transfer_to", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Account>> getAccountTransfersTo(@PathVariable("id") String accountId) {
    AccessorResponse<MdxList<Account>> response = gateway().transfers().accounts().toAccounts(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
