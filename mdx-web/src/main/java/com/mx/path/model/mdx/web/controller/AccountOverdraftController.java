package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.account.Overdraft;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}")
public class AccountOverdraftController extends BaseController {
  @RequestMapping(value = "/users/{userId}/accounts/{id}/overdraft", method = RequestMethod.GET)
  public final ResponseEntity<Overdraft> get(@PathVariable("id") String accountId) throws Exception {
    ensureFeature("accounts");
    AccessorResponse<Overdraft> response = gateway().accounts().accountOverdrafts().get(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{id}/overdraft", method = RequestMethod.PUT)
  public final ResponseEntity<Overdraft> update(@PathVariable("id") String accountId, @RequestBody Overdraft overdraft) {
    ensureFeature("accounts");
    AccessorResponse<Overdraft> response = gateway().accounts().accountOverdrafts().update(accountId, overdraft);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
