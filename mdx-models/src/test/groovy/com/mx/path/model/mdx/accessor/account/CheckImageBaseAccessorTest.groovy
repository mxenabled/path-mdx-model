package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class CheckImageBaseAccessorTest extends Specification {
  class TestAccessor extends CheckImageBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("accountId", "transactionId", "checkNumber", null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
