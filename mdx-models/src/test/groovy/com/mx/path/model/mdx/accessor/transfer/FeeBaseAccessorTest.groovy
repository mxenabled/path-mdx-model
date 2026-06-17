package com.mx.path.model.mdx.accessor.transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class FeeBaseAccessorTest extends Specification {
  class TestAccessor extends FeeBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list by id throws"() {
    when: subject.list("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list by options throws"() {
    when: subject.list((com.mx.path.model.mdx.model.transfer.options.FeeListOptions) null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
