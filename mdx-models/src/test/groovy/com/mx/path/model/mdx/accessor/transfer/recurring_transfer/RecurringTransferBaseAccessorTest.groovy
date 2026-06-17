package com.mx.path.model.mdx.accessor.transfer.recurring_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class RecurringTransferBaseAccessorTest extends Specification {
  class TestAccessor extends RecurringTransferBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "delete throws"() {
    when: subject.delete("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "skipNext throws"() {
    when: subject.skipNext("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "frequencies throws when not set"() {
    when: subject.frequencies()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "frequencies returns when set"() {
    given:
    def mock = Mock(FrequencyBaseAccessor)
    subject.setFrequencies(mock)
    expect: subject.frequencies() == mock
  }
}
