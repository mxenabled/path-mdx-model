package com.mx.web.mdx.controllers;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.cross_account_transfer.options.FeeListOptions;
import com.mx.models.transfer.Fee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class CrossAccountTransferFeesController extends BaseController {

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/fees", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Fee>> list(FeeListOptions options) {
    AccessorResponse<MdxList<Fee>> response = gateway().crossAccount().fees().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
