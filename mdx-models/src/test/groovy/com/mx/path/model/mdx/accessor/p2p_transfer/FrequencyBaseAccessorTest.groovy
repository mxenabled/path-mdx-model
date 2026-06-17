package com.mx.path.model.mdx.accessor.p2p_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class FrequencyBaseAccessorTest extends Specification {
  class TestAccessor extends FrequencyBaseAccessor {
    TestAccessor() {
      super()
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
