package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import com.google.gson.Gson
import com.mx.path.core.common.accessor.PathResponseStatus
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.products.ProductGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.products.Product
import com.mx.path.model.mdx.model.products.ProductSearch

import org.springframework.http.HttpStatus

import spock.lang.Specification

import jakarta.servlet.http.HttpServletRequest


class ProductControllerTest extends Specification {
  ProductController subject
  Gateway gateway
  ProductGateway productGateway
  Gson gson = new Gson()

  void setup() {
    subject = new ProductController()
    productGateway = spy(ProductGateway.builder().build())

    gateway = Gateway.builder()
        .products(productGateway)
        .build()

    BaseController.setGateway(gateway)
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def buildRequest(Object body, String contentType) {
    HttpServletRequest request = mock(HttpServletRequest.class)
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(gson.toJson(body))))
    when(request.getHeaders("Content-Type")).thenReturn(Collections.enumeration([contentType]))
    when(request.getHeaders("Accept")).thenReturn(Collections.enumeration([contentType]))
    return request
  }

  def "listProducts interacts with gateway"() {
    given:
    def search = new ProductSearch().tap {
      type = "ACCOUNT"
    }
    def mockResponse = new AccessorResponse<MdxList<Product>>().withResult(new MdxList<>().tap {
      add(new Product())
    })

    when:
    doReturn(mockResponse).when(productGateway).list(search)
    def response = subject.listProducts(search)

    then:
    verify(productGateway).list(search) || true
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "getProduct interacts with gateway - 200 status"() {
    given:
    def productId = "ID_1"
    def mockResponse = new AccessorResponse<Product>().withResult(new Product())

    when:
    doReturn(mockResponse).when(productGateway).get(productId)
    def response = subject.getProduct(productId)

    then:
    verify(productGateway).get(productId) || true
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "getProduct interacts with gateway - 202 status"() {
    given:
    def productId = "ID_1"
    def mockResponse = new AccessorResponse<Product>().withResult(
        new Product().tap {
          challenges = [new Challenge()]
        }
    )

    when:
    doReturn(mockResponse).when(productGateway).get(productId)
    def response = subject.getProduct(productId)

    then:
    verify(productGateway).get(productId) || true
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "getProduct interacts with gateway - 404 status"() {
    given:
    def productId = "ID_1"
    def mockResponse = new AccessorResponse<Product>()

    when:
    doReturn(mockResponse).when(productGateway).get(productId)
    def response = subject.getProduct(productId)

    then:
    verify(productGateway).get(productId) || true
    response.statusCode == HttpStatus.NOT_FOUND
  }

  def "updateProduct interacts with gateway - 200 status"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>().withResult(new Product())

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "updateProduct interacts with gateway - 202 status"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>().withResult(
        new Product().tap {
          challenges = [new Challenge()]
        }
    )

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "updateProduct interacts with gateway - 404 status"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>()

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    response.statusCode == HttpStatus.NOT_FOUND
  }

  def "updateProduct - accessor-set OK status wins over non-empty challenges (Rev 6 terminal response)"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>().withResult(
        new Product().tap {
          challenges = [new Challenge()]
        }
    ).withStatus(PathResponseStatus.OK)

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    response.statusCode == HttpStatus.OK
  }

  def "updateProduct - no accessor status with non-empty challenges still returns 202 (unchanged default)"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>().withResult(
        new Product().tap {
          challenges = [new Challenge()]
        }
    )

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "updateProduct - no accessor status with empty challenges still returns 200 (unchanged default)"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>().withResult(new Product())

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    response.statusCode == HttpStatus.OK
  }

  def "updateProduct - accessor signals NO_CONTENT when product is no longer available"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>().withStatus(PathResponseStatus.NO_CONTENT)

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    response.statusCode == HttpStatus.NO_CONTENT
    response.body == null
  }

  def "updateProduct - product id not found still returns 404 even though NO_CONTENT exists"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>()

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    response.statusCode == HttpStatus.NOT_FOUND
  }

  def "updateProduct v20260529 interacts with gateway - dispatches to update20260529"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>().withResult(
        new Product().tap {
          challenges = [new Challenge()]
        }
    )

    when:
    doReturn(mockResponse).when(productGateway).update20260529(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json;version=20260529"))

    then:
    verify(productGateway).update20260529(eq(productId), any(Product.class)) || true
    verify(productGateway, org.mockito.Mockito.never()).update(any(String.class), any(Product.class)) || true
    response.body == mockResponse.result
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "updateProduct without version header still dispatches to default update (unaffected by new version)"() {
    given:
    def productId = "ID_1"
    def product = new Product()
    def mockResponse = new AccessorResponse<Product>().withResult(new Product())

    when:
    doReturn(mockResponse).when(productGateway).update(eq(productId), any(Product.class))
    def response = subject.updateProduct(productId, buildRequest(product, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(productGateway).update(eq(productId), any(Product.class)) || true
    verify(productGateway, org.mockito.Mockito.never()).update20260529(any(String.class), any(Product.class)) || true
    response.statusCode == HttpStatus.OK
  }

  def "getProduct - accessor-set OK status wins over non-empty challenges"() {
    given:
    def productId = "ID_1"
    def mockResponse = new AccessorResponse<Product>().withResult(
        new Product().tap {
          challenges = [new Challenge()]
        }
    ).withStatus(PathResponseStatus.OK)

    when:
    doReturn(mockResponse).when(productGateway).get(productId)
    def response = subject.getProduct(productId)

    then:
    verify(productGateway).get(productId) || true
    response.statusCode == HttpStatus.OK
  }

  def "getProduct - accessor signals NO_CONTENT when product is no longer available"() {
    given:
    def productId = "ID_1"
    def mockResponse = new AccessorResponse<Product>().withStatus(PathResponseStatus.NO_CONTENT)

    when:
    doReturn(mockResponse).when(productGateway).get(productId)
    def response = subject.getProduct(productId)

    then:
    verify(productGateway).get(productId) || true
    response.statusCode == HttpStatus.NO_CONTENT
    response.body == null
  }
}
