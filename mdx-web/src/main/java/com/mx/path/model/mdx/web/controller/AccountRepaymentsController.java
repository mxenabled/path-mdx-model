package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.Repayment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AccountRepaymentsController extends BaseController {

  public AccountRepaymentsController() {
  }

  @RequestMapping(value = "/users/{userId}/accounts/{id}/repayments", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Repayment>> repayments(@PathVariable("id") String accountId) {
    AccessorResponse<MdxList<Repayment>> response = gateway().accounts().repayments().list(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
