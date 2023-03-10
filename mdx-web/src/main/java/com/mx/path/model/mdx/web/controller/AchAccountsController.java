package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.account.Account;
import com.mx.models.ach_transfer.AchAccount;
import com.mx.models.ach_transfer.options.AccountListOptions;
import com.mx.models.ach_transfer.options.AchAccountListOptions;
import com.mx.path.model.mdx.web.model.ach_transfer.AchTransferAccountListQueryParameters;
import com.mx.path.model.mdx.web.model.ach_transfer.AchTransferAchAccountListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AchAccountsController extends BaseController {

  @RequestMapping(value = "/users/{userId}/accounts/ach_transfer", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<Account>> listHeldAccounts(AchTransferAccountListQueryParameters queryParameters) {
    AccountListOptions options = new AccountListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    options.setListType(queryParameters.getList_type());
    options.setAccountId(queryParameters.getAccount_id());
    options.setAchAccountId(queryParameters.getAchAccount_id());
    AccessorResponse<MdxList<Account>> response = gateway().achTransfers().accounts().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers/ach_accounts", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<AchAccount>> list(AchTransferAchAccountListQueryParameters queryParameters) {
    AchAccountListOptions options = new AchAccountListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    options.setListType(queryParameters.getList_type());
    options.setAccountId(queryParameters.getAccount_id());
    options.setAchAccountId(queryParameters.getAchAccount_id());
    AccessorResponse<MdxList<AchAccount>> response = gateway().achTransfers().achAccounts().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers/ach_accounts/{id}", method = RequestMethod.GET)
  public final ResponseEntity<AchAccount> get(@PathVariable("id") String id) {
    AccessorResponse<AchAccount> response = gateway().achTransfers().achAccounts().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers/ach_accounts", method = RequestMethod.POST)
  public final ResponseEntity<AchAccount> create(@RequestBody AchAccount achAccount) {
    AccessorResponse<AchAccount> response = gateway().achTransfers().achAccounts().create(achAccount);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers/ach_accounts/{id}", method = RequestMethod.PUT)
  public final ResponseEntity<AchAccount> update(@PathVariable("id") String id, @RequestBody AchAccount achAccount) {
    achAccount.setId(id);
    AccessorResponse<AchAccount> response = gateway().achTransfers().achAccounts().update(id, achAccount);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/ach_transfers/ach_accounts/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<AchAccount> delete(@PathVariable("id") String id) {
    AccessorResponse<Void> response = gateway().achTransfers().achAccounts().delete(id);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }
}
