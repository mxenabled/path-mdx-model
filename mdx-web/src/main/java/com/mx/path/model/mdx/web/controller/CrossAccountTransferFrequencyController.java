package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.Frequency;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class CrossAccountTransferFrequencyController extends BaseController {

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/frequencies", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Frequency>> list() {
    AccessorResponse<MdxList<Frequency>> response = gateway().crossAccount().frequency().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
