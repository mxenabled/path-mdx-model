package com.mx.path.model.mdx.web.filter

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.mx.path.core.context.RequestContext
import com.mx.path.core.context.ResponseContext
import com.mx.path.core.context.store.SessionRepository

import spock.lang.Specification

class PathRequestContextFilterTest extends Specification {
  FilterChain filterChain
  HttpServletRequest request
  SessionRepository repository
  HttpServletResponse response

  PathRequestContextFilter subject
  RequestContext requestContext
  ResponseContext responseContext

  def setup() {
    filterChain = Mock()
    request = Mock()
    response = Mock()
    repository = Mock()
    subject = new PathRequestContextFilter()
  }

  def "filter with headers"() {
    given:
    filterChain.doFilter(request, response) >> {
      requestContext = RequestContext.current()
    }

    when:
    subject.doFilter(request, response, filterChain)

    then: "interacts with the request"
    1 * request.getHeader("mx-client-guid") >> "hf4h35-jf25jh453-hgf4h4"
    1 * request.getHeader("mx-user-guid") >> "76f5dg-dfg75dfg-sdg75fd"
    1 * request.getHeader("mx-feature") >> "feature"
    1 * request.getHeader("mx-device-ip-address") >> "192.13.14.12"
    1 * request.getHeader("mdx-job-type") >> "background"
    1 * request.getHeader("mx-session-trace-id") >> "session123"
    1 * request.getHeader("mx-device-trace-id") >> "device123"
    1 * request.getHeader("mx-refresh-token") >> "afdsfeadfclvkdds"
    1 * request.getRequestURI() >> "/afcu/users/usr1234/accounts/acc123/transactions/recent"
    1 * request.getHeaderNames() >> Collections.enumeration(["mx-refresh-token"])

    and: "has values set in requestContext"
    requestContext.getUserGuid() == "76f5dg-dfg75dfg-sdg75fd"
    requestContext.getUserId() == "usr1234"
    requestContext.getClientId() == "afcu"
    requestContext.getClientGuid() == "hf4h35-jf25jh453-hgf4h4"
    requestContext.getFeature() == "feature"
    requestContext.getPath() == "/afcu/users/usr1234/accounts/acc123/transactions/recent"
    requestContext.getOriginatingIP() == "192.13.14.12"
    requestContext.getHeaders().get("mdx-job-type") == "background"
    requestContext.getDeviceTraceId() == "device123"
    requestContext.getSessionTraceId() == "session123"
    requestContext.getHeaders().get("mx-refresh-token") == "afdsfeadfclvkdds"
  }

  def "filter without headers"() {
    given:
    filterChain.doFilter(request, response) >> {
      requestContext = RequestContext.current()
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * request.getHeader("mx-client-guid") >> null
    1 * request.getHeader("mx-feature") >> null
    1 * request.getHeader("mx-user-guid") >> null
    1 * request.getRequestURI() >> null

    and: "has values set in MDC"
    requestContext.getUserId() == null
    requestContext.getClientId() == null
    requestContext.getUserGuid() == null
    requestContext.getClientGuid() == null
    requestContext.getFeature() == null
    requestContext.getPath() == null
    requestContext.getOriginatingIP() == null
  }
}
