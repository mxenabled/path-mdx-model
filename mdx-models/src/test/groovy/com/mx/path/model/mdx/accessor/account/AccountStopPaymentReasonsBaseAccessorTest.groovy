package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountStopPaymentReasonsBaseAccessorTest extends Specification {
  class TestAccessor extends AccountStopPaymentReasonsBaseAccessor {
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
