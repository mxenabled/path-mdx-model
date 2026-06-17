package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class RepaymentBaseAccessorTest extends Specification {
  class TestAccessor extends RepaymentBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list("id")
    then: thrown(AccessorMethodNotImplementedException)
  }
}
