package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.managed_cards.Merchant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class ManagedCardMerchantController extends BaseController {
  public ManagedCardMerchantController() {
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{card_id}/merchants", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Merchant>> list() throws Exception {
    AccessorResponse<MdxList<Merchant>> response = gateway().managedCards().merchants().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{card_id}/merchants/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Merchant> get(@PathVariable("id") String id) throws Exception {
    AccessorResponse<Merchant> response = gateway().managedCards().merchants().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{card_id}/merchants/{id}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<Merchant> update(@PathVariable("id") String id, @RequestBody Merchant cardRequest) throws Exception {
    AccessorResponse<Merchant> response = gateway().managedCards().merchants().update(id, cardRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
