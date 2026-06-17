package com.mx.path.model.mdx.accessor

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class StatusBaseAccessorTest extends Specification {
  class TestAccessor extends StatusBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
