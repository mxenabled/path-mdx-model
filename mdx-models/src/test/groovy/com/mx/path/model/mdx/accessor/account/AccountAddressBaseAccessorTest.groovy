package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountAddressBaseAccessorTest extends Specification {
  class TestAccessor extends AccountAddressBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "create throws"() {
    when: subject.create("accountId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "delete throws"() {
    when: subject.delete("accountId", "addressId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("accountId", "addressId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list("accountId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("accountId", "addressId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
