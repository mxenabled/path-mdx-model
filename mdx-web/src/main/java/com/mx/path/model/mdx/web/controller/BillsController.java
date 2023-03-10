package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.payment.Bill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class BillsController extends BaseController {

  public BillsController() {
  }

  @RequestMapping(value = "/users/{userId}/bills", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Bill>> getBillList() throws Exception {
    AccessorResponse<MdxList<Bill>> response = gateway().payments().bills().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/bill/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Bill> getBill(@PathVariable("id") String id) throws Exception {
    AccessorResponse<Bill> response = gateway().payments().bills().get(id);
    return new ResponseEntity<Bill>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
