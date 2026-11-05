package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.external_transfer.ExternalAccount;
import com.mx.path.model.mdx.model.external_transfer.options.AccountListOptions;
import com.mx.path.model.mdx.model.external_transfer.options.ExternalAccountListOptions;
import com.mx.path.model.mdx.web.model.external_transfer.ExternalAccountListQueryParameters;
import com.mx.path.model.mdx.web.model.external_transfer.ExternalTransferAccountListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class ExternalAccountsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/accounts/external_transfer", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Account>> listHeldAccounts(ExternalTransferAccountListQueryParameters queryParameters) {
    AccountListOptions options = new AccountListOptions();
    options.setAccountId(queryParameters.getAccount_id());
    options.setExternalAccountId(queryParameters.getExternal_account_id());
    options.setListType(queryParameters.getList_type());
    AccessorResponse<MdxList<Account>> response = gateway().externalTransfers().accounts().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/external_transfers/external_accounts", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<ExternalAccount>> list(ExternalAccountListQueryParameters queryParameters) {
    ExternalAccountListOptions options = new ExternalAccountListOptions();
    options.setAccountId(queryParameters.getAccount_id());
    options.setExternalAccountId(queryParameters.getExternal_account_id());
    options.setListType(queryParameters.getList_type());
    AccessorResponse<MdxList<ExternalAccount>> response = gateway().externalTransfers().externalAccounts().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/external_transfers/external_accounts/{id}", method = RequestMethod.GET)
  public final ResponseEntity<ExternalAccount> get(@PathVariable("id") String id) {
    AccessorResponse<ExternalAccount> response = gateway().externalTransfers().externalAccounts().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/external_transfers/external_accounts", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<ExternalAccount> create(@RequestBody ExternalAccount externalAccountRequest) {
    AccessorResponse<ExternalAccount> response = gateway().externalTransfers().externalAccounts().create(externalAccountRequest);
    ExternalAccount result = response.getResult();
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), statusFor(result));
  }

  @RequestMapping(value = "/users/{userId}/external_transfers/external_accounts/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<ExternalAccount> update(@PathVariable("id") String id, @RequestBody ExternalAccount externalAccountRequest) {
    AccessorResponse<ExternalAccount> response = gateway().externalTransfers().externalAccounts().update(id, externalAccountRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/external_transfers/external_accounts/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> delete(@PathVariable("id") String id) {
    AccessorResponse<Void> response = gateway().externalTransfers().externalAccounts().delete(id);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  private static HttpStatus statusFor(ExternalAccount result) {
    if (result.getChallenges() != null && !result.getChallenges().isEmpty()) {
      return HttpStatus.ACCEPTED;
    }
    return HttpStatus.OK;
  }
}
