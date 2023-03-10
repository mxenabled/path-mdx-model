package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.managed_cards.ManagedCard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class ManagedCardsController extends BaseController {
  public ManagedCardsController() {
  }

  @RequestMapping(value = "/users/{userId}/managed_cards", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<ManagedCard>> list() throws Exception {
    AccessorResponse<MdxList<ManagedCard>> response = gateway().managedCards().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}", method = RequestMethod.GET)
  public final ResponseEntity<ManagedCard> get(@PathVariable("id") String id) throws Exception {
    AccessorResponse<ManagedCard> response = gateway().managedCards().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}/enable", method = RequestMethod.PUT)
  public final ResponseEntity<?> enable(@PathVariable("id") String id) throws Exception {
    AccessorResponse<Void> response = gateway().managedCards().enable(id);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}/pause", method = RequestMethod.PUT)
  public final ResponseEntity<?> pause(@PathVariable("id") String id) throws Exception {
    AccessorResponse<Void> response = gateway().managedCards().disable(id);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<ManagedCard> create(@RequestBody ManagedCard cardRequest) throws Exception {
    AccessorResponse<ManagedCard> response = gateway().managedCards().create(cardRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}/replace", method = RequestMethod.PUT)
  public final ResponseEntity<ManagedCard> replace(@PathVariable("id") String id) throws Exception {
    AccessorResponse<ManagedCard> response = gateway().managedCards().replace(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<ManagedCard> update(@PathVariable("id") String id, @RequestBody ManagedCard cardRequest) throws Exception {
    AccessorResponse<ManagedCard> response = gateway().managedCards().update(id, cardRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}/pin", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<?> setPin(@PathVariable("id") String id, @RequestBody ManagedCard cardRequest) throws Exception {
    AccessorResponse<ManagedCard> response = gateway().managedCards().setPin(id, cardRequest);
    ManagedCard result = response.getResult();
    // Return 202 returning challenge questions
    if (result != null && result.getChallenges() != null && result.getChallenges().length > 0) {
      return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.ACCEPTED);
    }
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}/activate", method = RequestMethod.PUT)
  public final ResponseEntity<ManagedCard> activate(@PathVariable("id") String id) throws Exception {
    AccessorResponse<ManagedCard> response = gateway().managedCards().activate(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}/unmasked_card_number", method = RequestMethod.GET)
  public final ResponseEntity<ManagedCard> getUnmaskedCardNumber(@PathVariable("id") String id) throws Exception {
    AccessorResponse<ManagedCard> response = gateway().managedCards().getUnmaskedCardNumber(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{id}/cvv", method = RequestMethod.GET)
  public final ResponseEntity<ManagedCard> getCvv(@PathVariable("id") String id) throws Exception {
    AccessorResponse<ManagedCard> response = gateway().managedCards().getCvv(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

}
