package com.mx.path.model.mdx.web

import com.mx.path.model.mdx.web.PathTools

import spock.lang.Specification

class PathToolsTest extends Specification {

  def "sessionIsNotRequiredOnAuthentication"() {
    expect:
    !PathTools.isSessionRequiredPath("/hughes/authentications")
    !PathTools.isSessionRequiredPath("/hughes/authentications/start")
  }

  def "sessionIsRequiredOnLogout"() {
    expect: "Logout has a session ID in the path"
    PathTools.isSessionRequiredPath("/hughes/authentications/session-1234")
  }

  def "sessionIsRequireOnUserRoutes"() {
    expect:
    PathTools.isSessionRequiredPath("/hughes/users/bob/accounts")
  }

  def "isAuthenticationPathIsTrue"() {
    expect:
    PathTools.isAuthenticationPath("/hughes/authentications")
    PathTools.isAuthenticationPath("/hughes/authentications/")
    PathTools.isAuthenticationPath("/hughes/authentications/start")
  }

  def "isUserRequiredPath"() {
    expect:
    !PathTools.isUserRequiredPath("/hughes/authentications")
    !PathTools.isUserRequiredPath("/hughes/authentications/")
    !PathTools.isUserRequiredPath("/hughes/authentications/start")
    PathTools.isUserRequiredPath("/hughes/users/U-197191/accounts")
  }

  def "isAuthenticationPathIsFalseOnLogoutAndMFARoutes"() {
    expect:
    !PathTools.isAuthenticationPath("/hughes/authentications/session-1234")
  }

  def "authenticationIsRequireOnUserRoutes"() {
    expect:
    PathTools.isAuthenticationRequiredPath("/hughes/users/bob/accounts")
  }

  def "authenticationIsNotRequireOnAuthenticationAndMFARoutes"() {
    expect:
    !PathTools.isAuthenticationRequiredPath("/hughes/authentications")
    !PathTools.isAuthenticationRequiredPath("/hughes/authentications/")
    !PathTools.isAuthenticationRequiredPath("/hughes/authentications/session-1234")
    !PathTools.isAuthenticationRequiredPath("/hughes/authentications/start")
  }

  def "extractSessionId"() {
    expect:
    PathTools.extractSessionId("/hughes/authentications/session-1234/sso") == "session-1234"
  }

  def "extractUserId"() {
    expect:
    PathTools.extractUserId("/hughes/users/U-197191/accounts") == "U-197191"
  }
}
