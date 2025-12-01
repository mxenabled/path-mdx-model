package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.p2p_transfer.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class P2PTransferDurationsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/p2p_transfers/durations", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Duration>> list() {
    AccessorResponse<MdxList<Duration>> response = gateway().p2pTransfers().durations().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
