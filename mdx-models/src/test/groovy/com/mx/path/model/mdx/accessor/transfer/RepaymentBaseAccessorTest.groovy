package com.mx.path.model.mdx.accessor.transfer

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
    when: subject.list("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
