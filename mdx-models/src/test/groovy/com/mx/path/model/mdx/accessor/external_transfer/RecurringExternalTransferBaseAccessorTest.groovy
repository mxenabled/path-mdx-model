package com.mx.path.model.mdx.accessor.external_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class RecurringExternalTransferBaseAccessorTest extends Specification {
  class TestAccessor extends RecurringExternalTransferBaseAccessor {
    TestAccessor() {
      super()
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
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "frequencies returns null when not set"() {
    expect: subject.frequencies() == null
  }

  def "frequencies returns when set"() {
    given:
    def mock = Mock(FrequencyBaseAccessor)
    subject.setFrequencies(mock)
    expect: subject.frequencies() == mock
  }
}
