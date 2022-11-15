package com.mx.web.mdx.controllers;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.transfer.Fee;
import com.mx.models.transfer.options.FeeListOptions;
import com.mx.web.mdx.models.Transfers.TransferFeesQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class TransferFeesController extends BaseController {

  @RequestMapping(value = "/users/{user_id}/transfers/{id}/fees", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Fee>> list(@PathVariable("id") String transferId, TransferFeesQueryParameters queryParameters) {
    AccessorResponse<MdxList<Fee>> response = gateway().transfers().fees().list(transferId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/transfers/fees", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Fee>> list(FeeListOptions options) {
    AccessorResponse<MdxList<Fee>> response = gateway().transfers().fees().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
