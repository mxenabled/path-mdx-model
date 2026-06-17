package com.mx.path.model.mdx.accessor.cross_account_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class FeeBaseAccessorTest extends Specification {
  class TestAccessor extends FeeBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list(null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
