package com.mx.web.mdx.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class StatusController extends BaseController {

  @RequestMapping(value = "/status", method = RequestMethod.GET)
  public final ResponseEntity<?> getStatus() {
    HttpStatus status = statusFromAccessorStatus(gateway().status().get().getStatus());
    return new ResponseEntity<>(status);
  }
}
