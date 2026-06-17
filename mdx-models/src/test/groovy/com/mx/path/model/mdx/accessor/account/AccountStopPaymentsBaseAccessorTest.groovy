package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountStopPaymentsBaseAccessorTest extends Specification {
  class TestAccessor extends AccountStopPaymentsBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "create throws"() {
    when: subject.create("accountId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list("accountId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "stopPaymentReasons throws when not set"() {
    when: subject.stopPaymentReasons()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "stopPaymentReasons returns when set"() {
    given:
    def mock = Mock(AccountStopPaymentReasonsBaseAccessor)
    subject.setStopPaymentReasons(mock)
    expect: subject.stopPaymentReasons() == mock
  }
}
