package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.AccountAddress;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AccountAddressController extends BaseController {

  public AccountAddressController() {
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<AccountAddress>> getAllAccountAddresses()
      throws Exception {
    AccessorResponse<MdxList<AccountAddress>> response = gateway().accounts().addresses().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses/{address_id}", method = RequestMethod.GET)
  public final ResponseEntity<AccountAddress> getAccountAddress(@PathVariable("addressId") String addressId) {
    AccessorResponse<AccountAddress> response = gateway().accounts().addresses().get(addressId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses/{address_id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deleteAccountAddress(@PathVariable("addressId") String addressId) {
    AccessorResponse<Void> response = gateway().accounts().addresses().delete(addressId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses", method = RequestMethod.POST)
  public final ResponseEntity<AccountAddress> createAccountAddress(@RequestBody AccountAddress address) {
    AccessorResponse<AccountAddress> response = gateway().accounts().addresses().create(address);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses/{address_id}", method = RequestMethod.PUT)
  public final ResponseEntity<AccountAddress> updateAccountAddress(@PathVariable("addressId") String addressId, @RequestBody AccountAddress address) {
    address.setId(addressId);
    AccessorResponse<AccountAddress> response = gateway().accounts().addresses().update(addressId, address);
    AccountAddress result = response.getResult();
    HttpStatus status = HttpStatus.OK;
    if (result.getChallenges() != null && result.getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    }
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), status);
  }
}
