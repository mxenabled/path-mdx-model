package com.mx.path.model.mdx.web.filter

import com.mx.path.core.common.accessor.UnauthorizedException
import com.mx.path.core.context.Session
import com.mx.path.core.context.store.SessionRepository

import spock.lang.Specification

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class RequireAuthenticationFilterTest extends Specification {
  FilterChain filterChain
  HttpServletRequest request
  SessionRepository repository
  HttpServletResponse response
  RequireAuthenticationFilter subject

  def setup() {
    filterChain = Mock()
    request = Mock()
    response = Mock()
    repository = Mock()
    Session.setRepositorySupplier({ -> repository })
    subject = new RequireAuthenticationFilter()
  }

  def cleanup() {
    Session.clearSession()
    Session.setRepositorySupplier(null)
  }

  def "authenticateWithNoSession"() {
    expect:
    Session.clearSession()
    setPath("/client/authentications")
    subject.doFilter(request, response, filterChain)
  }

  def "authenticateWithSession"() {
    expect:
    Session.createSession()
    setPath("/client/authentications")
    subject.doFilter(request, response, filterChain)
  }

  def "respondToMfaWithSessionInPinToPasswordState"() {
    expect:
    Session.createSession()
    Session.current().setSessionState(Session.SessionState.PINTOPASSWORD)
    setPath("/client/authentications/session-1234")
    subject.doFilter(request, response, filterChain)
  }

  def "respondToMfaWithSessionInChallengedState"() {
    expect:
    Session.createSession()
    Session.current().setSessionState(Session.SessionState.CHALLENGED)
    setPath("/client/authentications/session-1234")
    subject.doFilter(request, response, filterChain)
  }

  def "respondToMfaWithSessionInUnauthenticatedState"() {
    expect:
    Session.createSession()
    Session.current().setSessionState(Session.SessionState.UNAUTHENTICATED)
    setPath("/client/authentications/session-1234")
    subject.doFilter(request, response, filterChain)
  }

  def "respondToMfaWithNoSession"() {
    given:
    Session.clearSession()
    setPath("/client/authentications/session-1234")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    thrown(UnauthorizedException)
  }

  def "userRouteInChallengedState"() {
    given:
    Session.createSession()
    Session.current().setUserId("user123")
    Session.current().setSessionState(Session.SessionState.CHALLENGED)
    setPath("/client/users/user123/accounts")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    thrown(UnauthorizedException)
  }

  def "userRouteInPinToPasswordState"() {
    given:
    Session.createSession()
    Session.current().setUserId("user123")
    Session.current().setSessionState(Session.SessionState.PINTOPASSWORD)
    setPath("/client/users/user123/accounts")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    thrown(UnauthorizedException)
  }

  def "userRouteInAuthenticatedState"() {
    expect:
    Session.createSession()
    Session.current().setUserId("user123")
    Session.current().setSessionState(Session.SessionState.AUTHENTICATED)
    setPath("/client/users/user123/accounts")
    subject.doFilter(request, response, filterChain)
  }

  def "userRouteWithDifferentUserIdFromSessionUserId"() {
    given:
    Session.createSession()
    Session.current().setUserId("user123")
    Session.current().setSessionState(Session.SessionState.AUTHENTICATED)
    setPath("/hughes/users/U-197191/accounts")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    def err = thrown(UnauthorizedException)
    err.message == "User does not match user session"
    err.userMessage == "Not Authenticated"
  }

  def "userRouteWithMissingSessionUserId"() {
    given:
    Session.createSession()
    Session.current().setSessionState(Session.SessionState.AUTHENTICATED)
    setPath("/hughes/users/U-197191/accounts")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    def err = thrown(UnauthorizedException)
    err.message == "UserId missing in authenticated session"
    err.userMessage == "Not Authenticated"
  }

  private void setPath(String path) {
    request.getServletPath() >> path
  }
}
