package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.profile.Address;

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
  public final ResponseEntity<MdxList<Address>> getAllAccountAddresses(@PathVariable("account_id") String accountId)
      throws Exception {
    AccessorResponse<MdxList<Address>> response = gateway().accounts().addresses().list(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses/{address_id}", method = RequestMethod.GET)
  public final ResponseEntity<Address> getAccountAddress(@PathVariable("account_id") String accountId, @PathVariable("address_id") String addressId) {
    AccessorResponse<Address> response = gateway().accounts().addresses().get(accountId, addressId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses/{address_id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deleteAccountAddress(@PathVariable("account_id") String accountId, @PathVariable("address_id") String addressId) {
    AccessorResponse<Void> response = gateway().accounts().addresses().delete(accountId, addressId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses", method = RequestMethod.POST)
  public final ResponseEntity<Address> createAccountAddress(@PathVariable("account_id") String accountId, @RequestBody Address address) {
    AccessorResponse<Address> response = gateway().accounts().addresses().create(accountId, address);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/accounts/{account_id}/addresses/{address_id}", method = RequestMethod.PUT)
  public final ResponseEntity<Address> updateAccountAddress(@PathVariable("account_id") String accountId, @PathVariable("address_id") String addressId, @RequestBody Address address) {
    address.setId(addressId);
    AccessorResponse<Address> response = gateway().accounts().addresses().update(accountId, addressId, address);
    Address result = response.getResult();
    HttpStatus status = HttpStatus.OK;
    if (result.getChallenges() != null && result.getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    }
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), status);
  }
}
