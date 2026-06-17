package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class DisputeBaseAccessorTest extends Specification {
  class TestAccessor extends DisputeBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "start throws"() {
    when: subject.start(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "submit throws"() {
    when: subject.submit("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "cancel throws"() {
    when: subject.cancel("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "disputedTransactions throws when not set"() {
    when: subject.disputedTransactions()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "disputedTransactions returns when set"() {
    given:
    def mock = Mock(DisputedTransactionBaseAccessor)
    subject.setDisputedTransactions(mock)
    expect: subject.disputedTransactions() == mock
  }
}
