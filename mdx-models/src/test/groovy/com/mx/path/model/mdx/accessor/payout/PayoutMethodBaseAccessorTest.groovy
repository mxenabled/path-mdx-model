package com.mx.path.model.mdx.accessor.payout

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class PayoutMethodBaseAccessorTest extends Specification {
  class TestAccessor extends PayoutMethodBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "create throws"() {
    when: subject.create("recipientId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list("recipientId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("recipientId", "id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("recipientId", "id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "delete throws"() {
    when: subject.delete("recipientId", "id")
    then: thrown(AccessorMethodNotImplementedException)
  }
}
