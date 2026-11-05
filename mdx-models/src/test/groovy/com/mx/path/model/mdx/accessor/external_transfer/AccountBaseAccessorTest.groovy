package com.mx.path.model.mdx.accessor.external_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountBaseAccessorTest extends Specification {
  class TestAccessor extends AccountBaseAccessor {
    TestAccessor() {
      super()
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list(null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
