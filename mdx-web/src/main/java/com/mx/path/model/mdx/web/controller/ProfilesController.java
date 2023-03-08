package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.challenges.Challenge;
import com.mx.models.profile.Address;
import com.mx.models.profile.Email;
import com.mx.models.profile.Password;
import com.mx.models.profile.Phone;
import com.mx.models.profile.Profile;
import com.mx.models.profile.UserName;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class ProfilesController extends BaseController {

  public ProfilesController() {
  }

  @RequestMapping(value = "/users/{userId}/profile", method = RequestMethod.GET)
  public final ResponseEntity<Profile> getProfile() {
    AccessorResponse<Profile> response = gateway().profiles().get();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile", method = RequestMethod.PUT)
  public final ResponseEntity<Profile> createOrUpdateProfile(@RequestBody Profile profile) {
    AccessorResponse<Profile> response = gateway().profiles().update(profile);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/addresses", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Address>> getAddresses() {
    AccessorResponse<MdxList<Address>> response = gateway().profiles().addresses().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/addresses/{addressId}", method = RequestMethod.GET)
  public final ResponseEntity<Address> getAddress(@PathVariable("addressId") String addressId) {
    AccessorResponse<Address> response = gateway().profiles().addresses().get(addressId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/addresses", method = RequestMethod.POST)
  public final ResponseEntity<Address> createAddress(@RequestBody Address address) {
    AccessorResponse<Address> response = gateway().profiles().addresses().create(address);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/addresses/{addressId}", method = RequestMethod.PUT)
  public final ResponseEntity<Address> updateAddress(@PathVariable("addressId") String addressId, @RequestBody Address address) {
    address.setId(addressId);
    AccessorResponse<Address> response = gateway().profiles().addresses().update(addressId, address);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/addresses/{addressId}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deleteAddress(@PathVariable("addressId") String addressId) {
    AccessorResponse<Void> response = gateway().profiles().addresses().delete(addressId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/profile/phones", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Phone>> getPhones() {
    AccessorResponse<MdxList<Phone>> response = gateway().profiles().phones().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/phones/{phoneId}", method = RequestMethod.GET)
  public final ResponseEntity<Phone> getPhone(@PathVariable("phoneId") String phoneId) {
    AccessorResponse<Phone> response = gateway().profiles().phones().get(phoneId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/phones", method = RequestMethod.POST)
  public final ResponseEntity<Phone> createPhone(@RequestBody Phone phone) {
    AccessorResponse<Phone> response = gateway().profiles().phones().create(phone);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/phones/{phoneId}", method = RequestMethod.PUT)
  public final ResponseEntity<Phone> updatePhone(@PathVariable("phoneId") String phoneId, @RequestBody Phone phone) {
    phone.setId(phoneId);
    AccessorResponse<Phone> response = gateway().profiles().phones().update(phoneId, phone);
    Phone result = response.getResult();
    // Return 202 returning challenge questions
    HttpStatus status = HttpStatus.OK;
    if (result.getChallenges() != null && result.getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    }
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), status);
  }

  @RequestMapping(value = "/users/{userId}/profile/phones/{phoneId}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deletePhone(@PathVariable("phoneId") String phoneId) {
    AccessorResponse<Void> response = gateway().profiles().phones().delete(phoneId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/profile/emails", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Email>> getEmails() {
    AccessorResponse<MdxList<Email>> response = gateway().profiles().emails().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/emails/{emailId}", method = RequestMethod.GET)
  public final ResponseEntity<Email> getEmail(@PathVariable("emailId") String emailId) {
    AccessorResponse<Email> response = gateway().profiles().emails().get(emailId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/emails", method = RequestMethod.POST)
  public final ResponseEntity<Email> createEmail(@RequestBody Email email) {
    AccessorResponse<Email> response = gateway().profiles().emails().create(email);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/emails/{emailId}", method = RequestMethod.PUT)
  public final ResponseEntity<Email> updateEmail(@PathVariable("emailId") String emailId, @RequestBody Email email) {
    email.setId(emailId);
    AccessorResponse<Email> response = gateway().profiles().emails().update(emailId, email);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/profile/emails/{emailId}", method = RequestMethod.DELETE)
  public final ResponseEntity<Email> deleteEmail(@PathVariable("emailId") String emailId) {
    AccessorResponse<Void> response = gateway().profiles().emails().delete(emailId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/profile/update_password", method = RequestMethod.PUT)
  public final ResponseEntity<MdxList<Challenge>> updatePassword(@RequestBody Password password) {
    AccessorResponse<MdxList<Challenge>> response = gateway().profiles().updatePassword(password);
    if (response.getResult() != null && response.getResult().size() != 0) {
      return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/profile/update_password/challenges/{challengeId}", method = RequestMethod.PUT)
  public final ResponseEntity<MdxList<Challenge>> updatePasswordResume(@PathVariable("challengeId") String challengeId, @RequestBody Challenge challenge) {
    AccessorResponse<MdxList<Challenge>> response = gateway().profiles().updatePasswordResume(challengeId, challenge);
    if (response.getResult() != null && response.getResult().size() != 0) {
      return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/profile/update_username", method = RequestMethod.PUT)
  public final ResponseEntity<?> updateUserName(@RequestBody UserName updateUserInfo) {
    AccessorResponse<Void> response = gateway().profiles().updateUserName(updateUserInfo);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }
}
