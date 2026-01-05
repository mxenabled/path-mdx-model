package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class P2PTransferFrequenciesController extends BaseController {
  @RequestMapping(value = "/recurring_p2p_transfers/frequencies", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Frequency>> list() {
    AccessorResponse<MdxList<Frequency>> response = gateway().p2pTransfers().recurring().frequencies().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
