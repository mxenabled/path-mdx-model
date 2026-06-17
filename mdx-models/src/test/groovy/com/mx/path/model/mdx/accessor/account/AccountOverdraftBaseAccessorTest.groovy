package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountOverdraftBaseAccessorTest extends Specification {
  class TestAccessor extends AccountOverdraftBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
