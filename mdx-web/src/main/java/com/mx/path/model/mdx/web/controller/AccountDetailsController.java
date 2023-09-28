package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.account.AccountDetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}")
public class AccountDetailsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/accounts/{id}/account_details", method = RequestMethod.GET)
  public final ResponseEntity<AccountDetails> get(@PathVariable("id") String accountId) throws Exception {
    ensureFeature("accounts");
    AccessorResponse<AccountDetails> response = gateway().accounts().accountDetails().get(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
