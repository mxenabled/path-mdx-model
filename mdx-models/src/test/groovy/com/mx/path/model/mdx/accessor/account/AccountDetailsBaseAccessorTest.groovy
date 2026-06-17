package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountDetailsBaseAccessorTest extends Specification {
  class TestAccessor extends AccountDetailsBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }
}
