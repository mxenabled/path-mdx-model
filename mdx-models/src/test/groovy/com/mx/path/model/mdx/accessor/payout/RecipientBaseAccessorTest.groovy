package com.mx.path.model.mdx.accessor.payout

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class RecipientBaseAccessorTest extends Specification {
  class TestAccessor extends RecipientBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list(false)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "delete throws"() {
    when: subject.delete("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "payoutMethods throws when not set"() {
    when: subject.payoutMethods()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "payoutMethods returns when set"() {
    given:
    def mock = Mock(PayoutMethodBaseAccessor)
    subject.setPayoutMethods(mock)
    expect: subject.payoutMethods() == mock
  }
}
