package com.mx.models

import com.mx.models.account.Account
import com.mx.path.model.context.Session

import spock.lang.Specification

class UserIdProviderTest extends Specification {
  void cleanup() {
    Session.clearSession()
  }

  def "SetUserId"() {
    given:
    Session.createSession()
    Session.current().setUserId("user1234")

    def account = new Account()

    when:
    UserIdProvider.setUserIdProvider(null)
    UserIdProvider.setUserId(account)

    then:
    account.getUserId() == "user1234"
  }
}
