package com.mx.path.model.mdx.accessor.transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountBaseAccessorTest extends Specification {
  class TestAccessor extends AccountBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "fromAccounts throws"() {
    when: subject.fromAccounts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "toAccounts throws"() {
    when: subject.toAccounts("fromAccountId")
    then: thrown(AccessorMethodNotImplementedException)
  }
}
