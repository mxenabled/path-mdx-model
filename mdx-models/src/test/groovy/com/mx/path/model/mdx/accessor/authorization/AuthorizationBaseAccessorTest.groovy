package com.mx.path.model.mdx.accessor.authorization

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AuthorizationBaseAccessorTest extends Specification {
  class TestAccessor extends AuthorizationBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "callback throws"() {
    when: subject.callback("token")
    then: thrown(AccessorMethodNotImplementedException)
  }
}
