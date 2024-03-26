package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.account.alerts.AccountAlert;
import com.mx.path.model.mdx.model.account.alerts.DeliveryMethod;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}")
public class AccountAlertsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/accounts/{accountId}/alerts/{id}", method = RequestMethod.GET)
  public final ResponseEntity<AccountAlert> getAlert(@PathVariable("accountId") String accountId, @PathVariable("id") String alertId) {
    ensureFeature("accounts");
    AccessorResponse<AccountAlert> response = gateway().accounts().accountAlerts().get(accountId, alertId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{accountId}/alerts", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<AccountAlert>> getAlertList(@PathVariable("accountId") String accountId) {
    ensureFeature("accounts");
    AccessorResponse<MdxList<AccountAlert>> response = gateway().accounts().accountAlerts().list(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{accountId}/alerts", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<AccountAlert> createAlert(@PathVariable("accountId") String accountId, @RequestBody AccountAlert accountAlert) {
    ensureFeature("accounts");
    AccessorResponse<AccountAlert> response = gateway().accounts().accountAlerts().create(accountId, accountAlert);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{accountId}/alerts/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<AccountAlert> updateAlert(@PathVariable("accountId") String accountId, @RequestBody AccountAlert accountAlert) {
    ensureFeature("accounts");
    AccessorResponse<AccountAlert> response = gateway().accounts().accountAlerts().update(accountId, accountAlert);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{accountId}/alerts/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deleteAlert(@PathVariable("accountId") String accountId, @PathVariable("id") String alertId) {
    ensureFeature("accounts");
    AccessorResponse<Void> response = gateway().accounts().accountAlerts().delete(accountId, alertId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{accountId}/alerts/{id}/delivery_methods", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<DeliveryMethod>> getDeliveryMethods(@PathVariable("accountId") String accountId, @PathVariable("id") String alertId) {
    ensureFeature("accounts");
    AccessorResponse<MdxList<DeliveryMethod>> response = gateway().accounts().accountAlerts().deliveryMethods(accountId, alertId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/alert", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<Account>> getAccounts() {
    ensureFeature("accounts");
    AccessorResponse<MdxList<Account>> response = gateway().accounts().accountAlerts().accounts();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
