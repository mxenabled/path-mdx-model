package com.mx.path.model.mdx.accessor.payout

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class RecurringBaseAccessorTest extends Specification {
  class TestAccessor extends RecurringBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list()
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

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "cancel throws"() {
    when: subject.cancel("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "frequencies throws"() {
    when: subject.frequencies()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "skipNext throws"() {
    when: subject.skipNext("id")
    then: thrown(AccessorMethodNotImplementedException)
  }
}
