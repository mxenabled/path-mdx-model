package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.products.Product;
import com.mx.path.model.mdx.model.products.ProductSearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class ProductController extends BaseController {

  public ProductController() {
  }

  @RequestMapping(value = "/users/{userId}/products", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Product>> listProducts(ProductSearch productSearch) {
    AccessorResponse<MdxList<Product>> response = gateway().products().list(productSearch);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/products/{productId}", method = RequestMethod.GET)
  public final ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
    AccessorResponse<Product> response = gateway().products().get(productId);
    return buildResponse(response);
  }

  @RequestMapping(value = "/users/{userId}/products/{productId}", method = RequestMethod.PUT)
  public final ResponseEntity<Product> updateProduct(@PathVariable("productId") String productId, @RequestBody Product product) {
    product.setId(productId);
    AccessorResponse<Product> response = gateway().products().update(productId, product);
    return buildResponse(response);
  }

  private ResponseEntity<Product> buildResponse(AccessorResponse<Product> response) {
    Product result = response.getResult();
    if (result == null) {
      return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NOT_FOUND);
    }

    HttpStatus status = HttpStatus.OK;
    if (result.getChallenges() != null && result.getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    }
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), status);
  }

}
