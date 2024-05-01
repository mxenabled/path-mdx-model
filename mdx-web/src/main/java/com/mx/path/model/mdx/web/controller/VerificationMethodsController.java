package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.device.VerificationMethod;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class VerificationMethodsController extends BaseController {
  @RequestMapping(value = "/users/{userId}/verification_methods", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<VerificationMethod>> list() {
    AccessorResponse<MdxList<VerificationMethod>> response = gateway().devices().verificationMethods().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
