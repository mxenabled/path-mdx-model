package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.payment.Merchant;
import com.mx.models.payment.MerchantCategory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class MerchantsController extends BaseController {

  @RequestMapping(value = "/merchants", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Merchant>> getMerchantList(@RequestParam("name") String name) {
    AccessorResponse<MdxList<Merchant>> response = gateway().payments().merchants().list(name);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/merchants_categories", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<MerchantCategory>> getMerchantCategoryList() {
    AccessorResponse<MdxList<MerchantCategory>> response = gateway().payments().merchants().categories();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/merchant_categories/{merchant_category_id}/merchants", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Merchant>> getMerchantByCategoryList(
      @PathVariable("merchant_category_id") String merchantCategoryId) {
    AccessorResponse<MdxList<Merchant>> response = gateway().payments().merchants().search(merchantCategoryId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
