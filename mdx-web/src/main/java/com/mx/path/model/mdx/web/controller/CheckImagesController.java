package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.model.check.CheckImage;
import com.mx.path.model.mdx.model.check.options.CheckImageGetOptions;
import com.mx.path.model.mdx.web.model.check_image.CheckImagesGetQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public final class CheckImagesController extends BaseController {

  @RequestMapping(value = "/users/{userId}/accounts/{accountId}/transactions/{transId}/check_images/{checkId}", method = RequestMethod.GET)
  public ResponseEntity<CheckImage> getCheckImages(
      @PathVariable("accountId") String accountId,
      @PathVariable("transId") String transId,
      @PathVariable("checkId") String checkId, CheckImagesGetQueryParameters queryParameters) {

    CheckImageGetOptions options = new CheckImageGetOptions();
    options.setAmount(queryParameters.getAmount());
    options.setPostedOn(queryParameters.getPosted_on());

    AccessorResponse<CheckImage> response = gateway().accounts().transactions().checkImages().get(accountId, transId, checkId, options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

}
