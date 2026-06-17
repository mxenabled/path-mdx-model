package com.mx.path.model.mdx.accessor.ach_transfer.ach_scheduled_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AchScheduledTransferBaseAccessorTest extends Specification {
  class TestAccessor extends AchScheduledTransferBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "cancel throws"() {
    when: subject.cancel("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "create throws"() {
    when: subject.create(null)
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
