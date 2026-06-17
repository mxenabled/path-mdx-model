package com.mx.path.model.mdx.accessor.transfer.recurring_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class FrequencyBaseAccessorTest extends Specification {
  class TestAccessor extends FrequencyBaseAccessor {
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
