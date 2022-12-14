package com.mx.web.mdx.controllers;

import com.mx.common.accessors.AccessorResponse;
import com.mx.models.account.AccountOwner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}")
public class AccountOwnersController extends BaseController {
  @RequestMapping(value = "/accounts/{id}/account_owner", method = RequestMethod.GET, produces = BaseController.MDX_ONDEMAND_MEDIA)
  public final ResponseEntity<AccountOwner> get(@PathVariable("id") String accountId) throws Exception {
    AccessorResponse<AccountOwner> response = gateway().accounts().accountOwners().get(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
