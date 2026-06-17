package com.mx.path.model.mdx.accessor.payment

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class BillBaseAccessorTest extends Specification {
  class TestAccessor extends BillBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
