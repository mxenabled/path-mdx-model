package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.account.Account;
import com.mx.models.payout.Payout;
import com.mx.models.payout.PayoutMethod;
import com.mx.models.payout.Recipient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}/users/{user_id}", produces = BaseController.MDX_MEDIA)
public class PayoutsController extends BaseController {

  @RequestMapping(value = "/accounts/payout_from", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Account>> listPayoutFromAccounts() {
    AccessorResponse<MdxList<Account>> response = gateway().payouts().accounts();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payouts", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Payout> createPayout(@RequestBody Payout payoutRequest) {
    AccessorResponse<Payout> response = gateway().payouts().create(payoutRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payouts/{id}/cancel", method = RequestMethod.PUT)
  public final ResponseEntity<?> cancelPayout(@PathVariable("id") String payoutId) {
    AccessorResponse<Void> response = gateway().payouts().cancel(payoutId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/payouts", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Payout>> listPayouts() {
    AccessorResponse<MdxList<Payout>> response = gateway().payouts().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payouts/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Payout> getPayout(@PathVariable("id") String payoutId) {
    AccessorResponse<Payout> response = gateway().payouts().get(payoutId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Recipient> createRecipient(@RequestBody Recipient recipientRequest) {
    AccessorResponse<Recipient> response = gateway().payouts().recipients().create(recipientRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Recipient>> listRecipients(@RequestParam(value = "include_payout_methods", required = false) boolean includePayoutMethods) {
    AccessorResponse<MdxList<Recipient>> response = gateway().payouts().recipients().list(includePayoutMethods);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Recipient> getRecipient(@PathVariable("id") String recipientId) {
    AccessorResponse<Recipient> response = gateway().payouts().recipients().get(recipientId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Recipient> updateRecipient(@PathVariable("id") String recipientId, @RequestBody Recipient recipientRequest) {
    AccessorResponse<Recipient> response = gateway().payouts().recipients().update(recipientId, recipientRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deleteRecipient(@PathVariable("id") String recipientId) {
    AccessorResponse<Void> response = gateway().payouts().recipients().delete(recipientId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/recipients/{id}/payout_methods", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<PayoutMethod> createPayoutMethod(@PathVariable("id") String recipientId, @RequestBody PayoutMethod payoutMethodRequest) {
    AccessorResponse<PayoutMethod> response = gateway().payouts().recipients().payoutMethods().create(recipientId, payoutMethodRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients/{id}/payout_methods", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<PayoutMethod>> listPayoutMethods(@PathVariable("id") String recipientId) {
    AccessorResponse<MdxList<PayoutMethod>> response = gateway().payouts().recipients().payoutMethods().list(recipientId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients/{id}/payout_methods/{methodId}", method = RequestMethod.GET)
  public final ResponseEntity<PayoutMethod> getPayoutMethod(@PathVariable("id") String recipientId, @PathVariable("methodId") String methodId) {
    AccessorResponse<PayoutMethod> response = gateway().payouts().recipients().payoutMethods().get(recipientId, methodId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients/{id}/payout_methods/{methodId}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<PayoutMethod> updatePayoutMethod(@PathVariable("id") String recipientId, @PathVariable("methodId") String methodId, @RequestBody PayoutMethod payoutMethodRequest) {
    AccessorResponse<PayoutMethod> response = gateway().payouts().recipients().payoutMethods().update(recipientId, methodId, payoutMethodRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/recipients/{id}/payout_methods/{methodId}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deletePayoutMethod(@PathVariable("id") String recipientId, @PathVariable("methodId") String methodId) {
    AccessorResponse<Void> response = gateway().payouts().recipients().payoutMethods().delete(recipientId, methodId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/payouts/enroll", method = RequestMethod.POST)
  public final ResponseEntity<?> enroll() throws Exception {
    AccessorResponse<Void> response = gateway().payouts().enroll();
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }
}
