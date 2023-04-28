package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.account.AccountNumbers;

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
