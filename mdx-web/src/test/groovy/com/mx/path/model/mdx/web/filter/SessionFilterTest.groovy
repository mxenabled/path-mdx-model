package com.mx.path.model.mdx.web.filter

import com.mx.path.core.context.RequestContext
import com.mx.path.core.context.Session
import com.mx.path.core.context.store.SessionRepository

import spock.lang.Specification

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class SessionFilterTest extends Specification {
  FilterChain filterChain
  HttpServletRequest request
  SessionRepository repository
  HttpServletResponse response
  SessionFilter subject

  def setup() {
    filterChain = Mock()
    request = Mock()
    response = Mock()
    repository = Mock()
    Session.setRepositorySupplier({ -> repository })
    subject = new SessionFilter()
    RequestContext.builder().build().register()
  }

  def cleanup() {
    Session.clearSession()
    Session.setRepositorySupplier(null)
  }

  def "when session exists and authenticating it is deleted"() {
    given:
    Session.createSession()
    setSessionId(Session.current().getId())
    setPath("/client/authentications")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    Session.current() == null
  }

  def "when session exists using JWT Header and authenticating it is deleted"() {
    given:
    Session.createSession()
    setJWTHeader("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhZmN1Iiwic2Vzc2lvbklkIjoiZWRjZTQ1MDctZDVmMS00MjczLWI3YjQtYWZlYWZiMWJjOTM5IiwiZXhwIjoxNjU0ODExODI3LCJpYXQiOjE2NTQ4MTAwMjd9.MX12DEpT6S2srJGM0-CHct7qcHmm4FmWrdrnMK_Sggc")
    setPath("/client/authentications")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    Session.current() == null
  }

  private void setSession(String sessionId, Session session) {
    repository.load(sessionId) >> session
  }

  private void setPath(String path) {
    request.getServletPath() >> path
  }

  private void setSessionId(String sessionId) {
    request.getHeader(SessionFilter.SESSION_HEADER) >> Session.current().getId()
  }

  private void setJWTHeader(String jwt) {
    request.getHeader(SessionFilter.JWT_HEADER) >> jwt
  }
}
