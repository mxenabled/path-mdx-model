package com.mx.path.model.mdx.web

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

import javax.servlet.http.HttpServletRequest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mx.path.core.common.accessor.RequestValidationException
import com.mx.path.core.common.configuration.ConfigurationException
import com.mx.path.core.context.Session
import com.mx.path.model.mdx.model.Resources
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.testing.WithMockery

import org.springframework.http.ResponseEntity

import spock.lang.Specification

class DateVersionedResponseTest extends Specification implements WithMockery {
  Gson gson

  def setup() {
    GsonBuilder builder = new GsonBuilder()
    Resources.registerResources(builder)

    gson = builder.create()
  }

  def "rejects invalid version"() {
    when:
    def request = buildRequest(new Account(), "application/vnd.mdx.v6+json")
    def subject = new DateVersionedResponse(request)
        .version(18000101, Account, Account, { o ->
          return ResponseEntity.ok().build()
        })

    then:
    def ex = thrown(ConfigurationException)
    ex.message == "Invalid version specified in controller. Must be a numeric date in the format YYYYMMDD and must be greater than 19700101."
  }

  def "executes default version when no version provided"() {
    given:

    def request = buildRequest(new Account(), "application/vnd.mdx.v6+json")
    def executed = false
    def subject = new DateVersionedResponse(request)
        .defaultVersion(Account, Account, { o ->
          executed = true
          return ResponseEntity.ok().build()
        })
        .version(19700101, Account, Account, { o ->
          return ResponseEntity.ok().build()
        })

    when:
    def response = subject.execute()

    then:
    executed
    response.getHeaders().getContentType().toString() == "application/vnd.mx.mdx.v6+json;charset=UTF-8"
  }

  def "executes default version when provided version is older than all other versions"() {
    given:
    def request = buildRequest(new Account(), "application/vnd.mdx.v6+json;version=20231231")
    def executed = false
    def subject = new DateVersionedResponse(request)
        .defaultVersion(Account, Account, { o ->
          executed = true
          return ResponseEntity.ok().build()
        })
        .version(20240101, Account, Account, { o ->
          return ResponseEntity.ok().build()
        })
        .version(20250101, Account, Account, { o ->
          return ResponseEntity.ok().build()
        })

    when:
    def response = subject.execute()

    then:
    executed
    response.getHeaders().getContentType().toString() == "application/vnd.mx.mdx.v6+json;charset=UTF-8"
  }

  def "executes newest version before provided version"() {
    given:
    def request = buildRequest(new Account(), "application/vnd.mdx.v6+json;version=20240102")
    def executed = false
    def subject = new DateVersionedResponse(request)
        .defaultVersion(Account, Account, { o ->
          return ResponseEntity.ok().build()
        })
        .version(20240101, Account, Account, { o ->
          executed = true
          return ResponseEntity.ok().build()
        })
        .version(20231231, Account, Account, { o ->
          return ResponseEntity.ok().build()
        })

    when:
    def response = subject.execute()

    then:
    executed
    response.getHeaders().getContentType().toString() == "application/vnd.mx.mdx.v6+json;charset=UTF-8;version=20240101"
  }

  def "raises Invalid Request if invalid version is provided"() {
    given:
    def request = buildRequest(new Account(), "application/vnd.mdx.v6+json;version=junk")

    when:
    new DateVersionedResponse(request)
        .defaultVersion(Account, Account, { o ->
          return ResponseEntity.ok().build()
        })
        .execute()

    then:
    def ex = thrown(RequestValidationException)
    ex.message == "Invalid version specified in header"
  }

  def buildRequest(Object body, String contentType) {
    HttpServletRequest request = mock(HttpServletRequest.class)
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(gson.toJson(body))))
    if (Session.current() != null) {
      when(request.getHeader("mx-session-key")).thenReturn(Session.current().getId())
    }
    when(request.getHeaders("Content-Type")).thenReturn(Collections.enumeration([contentType]))
    when(request.getHeaders("Accept")).thenReturn(Collections.enumeration([contentType]))

    return request
  }
}
