package com.mx.path.model.mdx.accessor.payout

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class PayoutBaseAccessorTest extends Specification {
  class TestAccessor extends PayoutBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "accounts throws"() {
    when: subject.accounts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "cancel throws"() {
    when: subject.cancel("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "enroll throws"() {
    when: subject.enroll()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "recipients throws when not set"() {
    when: subject.recipients()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "recipients returns when set"() {
    given:
    def mock = Mock(RecipientBaseAccessor)
    subject.setRecipients(mock)
    expect: subject.recipients() == mock
  }

  def "recurring throws when not set"() {
    when: subject.recurring()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "recurring returns when set"() {
    given:
    def mock = Mock(RecurringBaseAccessor)
    subject.setRecurring(mock)
    expect: subject.recurring() == mock
  }
}
