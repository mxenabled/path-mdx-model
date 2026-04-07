package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.alerts.Alert;
import com.mx.path.model.mdx.model.alerts.DeliveryMethod;
import com.mx.path.model.mdx.model.managed_cards.ManagedCard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}")
public class ManagedCardAlertsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/managed_cards/{cardId}/alerts/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Alert> getAlert(@PathVariable("cardId") String cardId, @PathVariable("id") String alertId) {
    AccessorResponse<Alert> response = gateway().managedCards().alerts().get(cardId, alertId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{cardId}/alerts", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<Alert>> getAlertList(@PathVariable("cardId") String cardId) {
    AccessorResponse<MdxList<Alert>> response = gateway().managedCards().alerts().list(cardId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{cardId}/alerts/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Alert> updateAlert(@PathVariable("cardId") String cardId, @RequestBody Alert alert) {
    AccessorResponse<Alert> response = gateway().managedCards().alerts().update(cardId, alert);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/{cardId}/alerts/{id}/delivery_methods", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<DeliveryMethod>> getDeliveryMethods(@PathVariable("cardId") String cardId, @PathVariable("id") String alertId) {
    AccessorResponse<MdxList<DeliveryMethod>> response = gateway().managedCards().alerts().deliveryMethods(cardId, alertId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/managed_cards/alert", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<ManagedCard>> getCards() {
    AccessorResponse<MdxList<ManagedCard>> response = gateway().managedCards().alerts().cards();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
