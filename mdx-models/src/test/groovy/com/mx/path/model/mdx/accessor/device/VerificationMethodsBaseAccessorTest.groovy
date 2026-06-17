package com.mx.path.model.mdx.accessor.device

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class VerificationMethodsBaseAccessorTest extends Specification {
  class TestAccessor extends VerificationMethodsBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
