package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.StopPaymentReason;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}")
public class AccountStopPaymentReasonsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/accounts/stop_payment_reasons", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<StopPaymentReason>> list() throws Exception {
    ensureFeature("accounts");
    AccessorResponse<MdxList<StopPaymentReason>> response = gateway().accounts().accountStopPayments().stopPaymentReasons().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
