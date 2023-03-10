package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.payment.Payee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}/users/{user_id}", produces = BaseController.MDX_MEDIA)
public class PayeesController extends BaseController {

  @RequestMapping(value = "/payees", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Payee> addPayee(@RequestBody Payee payeeRequest) {
    AccessorResponse<Payee> accessorResponse = gateway().payments().payees().create(payeeRequest);
    HttpStatus status = accessorResponse.getStatus() != null ? HttpStatus.valueOf(accessorResponse.getStatus().value()) : HttpStatus.OK;
    return new ResponseEntity<>(accessorResponse.getResult().wrapped(), createMultiMapForResponse(accessorResponse.getHeaders()), status);
  }

  @RequestMapping(value = "/payees", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Payee>> getPayeeList() {
    AccessorResponse<MdxList<Payee>> response = gateway().payments().payees().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payees/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Payee> getPayee(@PathVariable("id") String payeeId) {
    AccessorResponse<Payee> response = gateway().payments().payees().get(payeeId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/payees/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deletePayee(@PathVariable("id") String payeeId) {
    AccessorResponse<Void> response = gateway().payments().payees().delete(payeeId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/payees/{id}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Payee> updatePayee(@PathVariable("id") String payeeId, @RequestBody Payee payeeRequest) {
    payeeRequest.setId(payeeId);
    AccessorResponse<Payee> response = gateway().payments().payees().update(payeeId, payeeRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
