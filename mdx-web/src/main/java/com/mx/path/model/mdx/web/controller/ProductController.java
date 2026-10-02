package com.mx.path.model.mdx.web.controller;

import com.mx.path.core.common.accessor.PathResponseStatus;
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
    PathResponseStatus accessorStatus = response.getStatus();

    // An accessor can explicitly signal that a product is no longer available. This takes
    // precedence over the null-result check below -- a product that existed and is now gone is a
    // different case than a product id that never existed (still 404).
    if (accessorStatus == PathResponseStatus.NO_CONTENT) {
      return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
    }

    Product result = response.getResult();
    if (result == null) {
      return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NOT_FOUND);
    }

    HttpStatus status;
    if (accessorStatus != null) {
      // Respect an explicit accessor status (e.g. OK alongside a trailing success challenge) rather
      // than inferring it purely from the presence of challenges. Mirrors PayeesController.addPayee().
      status = HttpStatus.valueOf(accessorStatus.value());
    } else if (result.getChallenges() != null && result.getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    } else {
      status = HttpStatus.OK;
    }
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), status);
  }

}
