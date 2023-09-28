package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.StopPayment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}")
public class AccountStopPaymentsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/accounts/{id}/stop_payments", method = RequestMethod.POST)
  public final ResponseEntity<StopPayment> create(@PathVariable("id") String accountId, @RequestBody StopPayment stopPayment) {
    ensureFeature("accounts");
    AccessorResponse<StopPayment> response = gateway().accounts().accountStopPayments().create(accountId, stopPayment);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{id}/stop_payments", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<StopPayment>> list(@PathVariable("id") String accountId) throws Exception {
    ensureFeature("accounts");
    AccessorResponse<MdxList<StopPayment>> response = gateway().accounts().accountStopPayments().list(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
