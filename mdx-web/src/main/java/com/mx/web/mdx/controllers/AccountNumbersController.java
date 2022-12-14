package com.mx.web.mdx.controllers;

import com.mx.common.accessors.AccessorResponse;
import com.mx.models.account.AccountNumbers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}")
public class AccountNumbersController extends BaseController {
  @RequestMapping(value = "/accounts/{id}/account_numbers", method = RequestMethod.GET, produces = BaseController.MDX_ONDEMAND_MEDIA)
  public final ResponseEntity<AccountNumbers> get(@PathVariable("id") String accountId) throws Exception {
    AccessorResponse<AccountNumbers> response = gateway().accounts().accountNumbers().get(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
