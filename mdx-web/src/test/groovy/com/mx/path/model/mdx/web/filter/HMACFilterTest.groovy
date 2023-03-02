package com.mx.path.model.mdx.web.filter

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.mx.common.accessors.ResourceNotFoundException
import com.mx.path.model.context.Session
import com.mx.path.model.context.store.SessionRepository
import com.mx.path.model.mdx.web.filter.hmac.HMACConfiguration
import com.mx.path.model.mdx.web.filter.hmac.HMACConfigurationFactory

import spock.lang.Specification

class HMACFilterTest extends Specification {

  FilterChain filterChain
  HttpServletRequest request
  SessionRepository repository
  HttpServletResponse response
  HMACFilter subject

  def setup() {
    filterChain = Mock()
    request = Mock()
    response = Mock()
    repository = Mock()
    Session.setRepositorySupplier({ -> repository })
    subject = new HMACFilter()
  }

  def cleanup() {
    Session.clearSession()
    Session.setRepositorySupplier(null)
  }

  def "skips when factory is null"() {
    given:
    Session.clearSession()
    subject.setHmacConfigurationFactory(null)
    setPath("/client/authentications/session-1234")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    true
  }

  def "validates when factory is provided"() {
    given:
    Session.clearSession()
    HMACConfiguration configuration = Mock()
    HMACConfigurationFactory factory = Mock()
    factory.get("client") >> configuration
    subject.setHmacConfigurationFactory(factory)
    setPath("/client/authentications/session-1234")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    thrown(ResourceNotFoundException.class)
    1 * factory.get("client")
  }

  private void setSession(String sessionId, Session session) {
    repository.load(sessionId) >> session
  }

  private void setPath(String path) {
    request.getServletPath() >> path
    request.getRequestURI() >> path
  }

  private void setSessionId(String sessionId) {
    request.getHeader(_) >> Session.current().getId()
  }
}
