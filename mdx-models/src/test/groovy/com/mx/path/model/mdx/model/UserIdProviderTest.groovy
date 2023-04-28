package com.mx.path.model.mdx.model

import com.mx.path.core.context.Session
import com.mx.path.model.mdx.model.account.Account

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
    UserIdProvider.setUserId(account)

    then:
    account.getUserId() == "user1234"
  }
}
