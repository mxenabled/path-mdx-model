package com.mx.path.model.mdx.accessor.remote_deposit

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class RemoteDepositBaseAccessorTest extends Specification {
  class TestAccessor extends RemoteDepositBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accounts throws"() {
    when: subject.accounts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "limits throws"() {
    when: subject.limits()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
