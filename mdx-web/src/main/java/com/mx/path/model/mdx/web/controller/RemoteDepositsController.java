package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.remote_deposit.RemoteDeposit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class RemoteDepositsController extends BaseController {

  public RemoteDepositsController() {
  }

  @RequestMapping(value = "/users/{user_id}/remote_deposits", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<RemoteDeposit> createRemoteDeposit(@RequestBody RemoteDeposit remoteDepositRequest) {
    AccessorResponse<RemoteDeposit> response = gateway().remoteDeposits().create(remoteDepositRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/remote_deposits/{id}", method = RequestMethod.GET)
  public final ResponseEntity<RemoteDeposit> getRemoteDeposit(@PathVariable("id") String remoteDepositId) {
    AccessorResponse<RemoteDeposit> response = gateway().remoteDeposits().get(remoteDepositId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/remote_deposits", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<RemoteDeposit>> getRemoteDeposits() {
    AccessorResponse<MdxList<RemoteDeposit>> response = gateway().remoteDeposits().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/remote_deposit", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Account>> getRemoteDepositAccounts() {
    AccessorResponse<MdxList<Account>> response = gateway().remoteDeposits().accounts();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
