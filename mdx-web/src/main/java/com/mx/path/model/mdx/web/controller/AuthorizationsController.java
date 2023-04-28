package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.authorization.Authorization;
import com.mx.path.model.mdx.model.authorization.HtmlPage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AuthorizationsController extends BaseController {

  public AuthorizationsController() {
  }

  @RequestMapping(value = "/users/{user_id}/authorizations", method = RequestMethod.POST, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Authorization> createAuthorization(@RequestBody Authorization authorizationRequest) throws Exception {
    AccessorResponse<Authorization> response = gateway().authorizations().create(authorizationRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{user_id}/authorizations/callback", method = RequestMethod.GET, produces = "text/html")
  public final ResponseEntity<String> callback(@RequestParam String token) {
    HtmlPage htmlPage = gateway().authorizations().callback(token).getResult();
    return new ResponseEntity<>(htmlPage.getContent(), HttpStatus.OK);
  }
}
