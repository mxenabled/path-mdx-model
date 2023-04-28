package com.mx.path.model.mdx.web.filter.hmac

import com.mx.path.core.common.accessor.AccessorUserException
import com.mx.path.model.mdx.web.filter.MultiReadHttpServletRequest

import org.springframework.mock.web.MockHttpServletRequest

import spock.lang.Specification

class HMACTest extends Specification {

  def salt = "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVo3ODkwMTI="
  MockHttpServletRequest request

  def setup() {
    request = new MockHttpServletRequest()
    request.setMethod("POST")
    request.setServerPort(3001)
    request.setServerName("localhost")
    request.setRequestURI("/hughes/sessions")
    request.setContent("<?xml version=\"1.0\"?>\n<mdx version=\"5.0\">\n  <session>\n    <userkey><![CDATA[the-userkey]]></userkey>\n  </session>\n</mdx>\n".getBytes())
    request.addHeader("Authorization", "MDX sha256 6735b91165de81bb3715d6fba019f854be6dfec1c0e36948012b4abe2cbb9ab0")
    request.addHeader("MX-Session-Key", "")
    request.addHeader("Accept", "application/vnd.moneydesktop.mdx.v5+xml")
    request.setContentType("application/vnd.moneydesktop.mdx.v5+xml")
    request.addHeader("Date", "1382975431")
  }

  def "testVerifyHMAC"() {
    expect:
    MDXHmac.validateRequest(new MultiReadHttpServletRequest(request), salt)
  }

  def "testAuthenticationWithMDXv6"() {
    given:
    def md5Hex = HMAC.getMD5Hex("{\n  \"authentication\": {\n    \"login\": \"ftempleton\",\n    \"password\": \"B!ack999\"\n  }\n}")
    assert "c13b352a53430b3d1ca206670e8c6e0d" == md5Hex
    def hmacToSign = "POSTc13b352a53430b3d1ca206670e8c6e0dapplication/vnd.mx.mdx.v6+json1382975431http://localhost:3001/hughes/sessions"
    def signedHMAC = HMAC.generateHmacSignature(hmacToSign, salt, "HmacSHA256")

    expect:
    "c7af98678209ee5d2e5865ff7a6a84ee5ac7b6069e56e1beee2c76c994e1fc24" == signedHMAC
  }

  def "testMissingSession_throwsException"() {
    given:
    request.removeHeader("Authorization")

    when:
    MDXHmac.validateRequest(new MultiReadHttpServletRequest(request), salt)

    then: "Exception should have been triggered"
    def e = thrown(AccessorUserException)
    e.getMessage() == "Authorization header missing"
    e.getStatus().value() == 401
  }

  def "testMissingDate_throwsMdxException"() {
    given:
    request.removeHeader("Date")

    when:
    MDXHmac.validateRequest(new MultiReadHttpServletRequest(request), salt)

    then: "Exception should have been triggered"
    def e = thrown(AccessorUserException)
    e.getMessage() == "Date header missing"
    e.getStatus().value() == 401
  }
}
