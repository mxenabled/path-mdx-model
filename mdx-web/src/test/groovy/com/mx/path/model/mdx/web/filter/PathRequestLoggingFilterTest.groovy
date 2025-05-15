package com.mx.path.model.mdx.web.filter

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.mx.path.core.context.RequestContext

import org.slf4j.Logger
import org.slf4j.MDC

import spock.lang.Specification

class PathRequestLoggingFilterTest extends Specification {
  HttpServletRequest request
  HttpServletResponse response
  FilterChain filterChain
  PathRequestLoggingFilter subject
  Logger logger

  def setup() {
    logger = mock(Logger.class)
    PathRequestLoggingFilter.setLogger(logger)

    subject = new PathRequestLoggingFilter()
    request = mock(HttpServletRequest)
    response = mock(HttpServletResponse)
    filterChain = mock(FilterChain)

    RequestContext.builder()
        .clientId("client-id")
        .clientGuid("3a1a8ff6-778e-40e0-995a-72d6bf6ccb35")
        .userGuid("a851008c-d85a-4dcf-87f0-b1e2a69adaf3")
        .path("/testing")
        .deviceTraceId("device123")
        .sessionTraceId("ebebebe")
        .originatingIP("10.10.10.1")
        .feature("feature")
        .build()
        .register()
  }

  def cleanup() {
    PathRequestLoggingFilter.resetLogger()
    MDC.clear()
    RequestContext.clear()
  }

  def "interacts with the logger"() {
    when:
    subject.doFilterInternal(request, response, filterChain)
    verify(logger).info("Incoming request")

    then:
    MDC.get("client_guid") == null
    MDC.get("client_id") == null
    MDC.get("device_trace_id") == null
    MDC.get("exception") == null
    MDC.get("feature") == null
    MDC.get("ip_address") == null
    MDC.get("log_guid") == null
    MDC.get("path") == null
    MDC.get("query_params") == null
    MDC.get("request_body") == null
    MDC.get("request_duration") == null
    MDC.get("request_headers") == null
    MDC.get("request_headers_json") == null
    MDC.get("request_method") == null
    MDC.get("response_body") == null
    MDC.get("response_headers") == null
    MDC.get("response_headers_json") == null
    MDC.get("session_trace_id") == null
    MDC.get("status") == null
    MDC.get("user_guid") == null
  }

  def "with empty request context and response"() {
    given:
    RequestContext.builder()
        .build()
        .register()

    when:
    subject.doFilterInternal(request, response, filterChain)
    verify(logger).info("Incoming request")

    then:
    MDC.get("client_guid") == null
    MDC.get("client_id") == null
    MDC.get("device_trace_id") == null
    MDC.get("exception") == null
    MDC.get("feature") == null
    MDC.get("ip_address") == null
    MDC.get("log_guid") == null
    MDC.get("path") == null
    MDC.get("query_params") == null
    MDC.get("request_body") == null
    MDC.get("request_duration") == null
    MDC.get("request_headers") == null
    MDC.get("request_headers_json") == null
    MDC.get("request_method") == null
    MDC.get("response_body") == null
    MDC.get("response_headers") == null
    MDC.get("response_headers_json") == null
    MDC.get("session_trace_id") == null
    MDC.get("status") == null
    MDC.get("user_guid") == null
  }

  def "exception during logging caught and logged"() {
    given:
    def testException = new RuntimeException("Test Exception")
    when(request.getMethod()).thenThrow(testException)

    when:
    subject.doFilterInternal(request, response, filterChain)
    verify(logger).error("Failed to log incoming request", testException)

    then:
    notThrown(Exception)
    MDC.get("client_guid") == null
    MDC.get("client_id") == null
    MDC.get("device_trace_id") == null
    MDC.get("exception") == null
    MDC.get("feature") == null
    MDC.get("ip_address") == null
    MDC.get("log_guid") == null
    MDC.get("path") == null
    MDC.get("query_params") == null
    MDC.get("request_body") == null
    MDC.get("request_duration") == null
    MDC.get("request_headers") == null
    MDC.get("request_headers_json") == null
    MDC.get("request_method") == null
    MDC.get("response_body") == null
    MDC.get("response_headers") == null
    MDC.get("response_headers_json") == null
    MDC.get("session_trace_id") == null
    MDC.get("status") == null
    MDC.get("user_guid") == null
  }

  def "captures expected data in MDC"() {
    given:
    def testNoMDCClearingSubject = new PathRequestLoggingFilterWithNoMDCClearing()
    when(request.getRequestURI()).thenReturn("/testing")
    when(request.getQueryString()).thenReturn("param1=value1&param2=value2")
    when(request.getMethod()).thenReturn("GET")

    def requestHeadersList = new ArrayList<String>().tap {
      add("Accept")
      add("mx-device-ip-address")
      add("x-request-token")
    }
    when(request.getHeaderNames()).thenReturn(Collections.enumeration(requestHeadersList))
    when(request.getHeader("Accept")).thenReturn("application/vnd.mx.mdx.v6+json")
    when(request.getHeader("mx-device-ip-address")).thenReturn("10.10.10.1")
    when(request.getHeader("x-request-token")).thenReturn("12345")

    def responseHeadersList = [
      "Content-Type",
      "Content-Length",
      "mx-session-key"
    ]
    when(response.getHeaderNames()).thenReturn(responseHeadersList)
    when(response.getHeaders("Content-Type")).thenReturn([
      "application/vnd.mx.mdx.v6+json"
    ])
    when(response.getHeaders("Content-Length")).thenReturn(["50"])
    when(response.getHeaders("mx-session-key")).thenReturn([
      "f3a370a8-397b-417b-992d-cc324819e311"
    ])

    when:
    testNoMDCClearingSubject.doFilterInternal(request, response, filterChain)

    then:
    MDC.get("log_guid") != null
    MDC.get("client_guid") == "3a1a8ff6-778e-40e0-995a-72d6bf6ccb35"
    MDC.get("client_id") == "client-id"
    MDC.get("path") == "/testing"
    MDC.get("user_guid") == "a851008c-d85a-4dcf-87f0-b1e2a69adaf3"
    MDC.get("feature") == "feature"
    MDC.get("ip_address") == "10.10.10.1"
    MDC.get("session_trace_id") == "ebebebe"
    MDC.get("device_trace_id") == "device123"
    MDC.get("request_method") == "GET"
    MDC.get("request_uri") == "/testing"
    MDC.get("query_params") == "param1: value1\nparam2: value2\n"
    MDC.get("request_headers_json") == "{\"Accept\":\"application/vnd.mx.mdx.v6+json\",\"x-request-token\":\"**MASKED**\",\"mx-device-ip-address\":\"10.10.10.1\"}"
    MDC.get("request_headers") == "Accept: application/vnd.mx.mdx.v6+json\nx-request-token: **MASKED**\nmx-device-ip-address: 10.10.10.1\n"
    MDC.get("request_body") == null
    MDC.get("request_duration") != null
    MDC.get("response_headers_json") == "{\"Content-Length\":\"50\",\"mx-session-key\":\"**MASKED**\",\"Content-Type\":\"application/vnd.mx.mdx.v6+json\"}"
    MDC.get("response_headers") == "Content-Length: 50\nmx-session-key: **MASKED**\nContent-Type: application/vnd.mx.mdx.v6+json\n"
  }

  private class PathRequestLoggingFilterWithNoMDCClearing extends PathRequestLoggingFilter {
    @Override
    protected void resetMDC() {
    }
  }
}
