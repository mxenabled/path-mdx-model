package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountAlertBaseAccessorTest extends Specification {
  class TestAccessor extends AccountAlertBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("accountId", "alertId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list("accountId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("accountId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "deliveryMethods throws"() {
    when: subject.deliveryMethods("accountId", "alertId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accounts throws"() {
    when: subject.accounts()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
