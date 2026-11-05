package com.mx.path.model.mdx.accessor.external_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class ExternalAccountBaseAccessorTest extends Specification {
  class TestAccessor extends ExternalAccountBaseAccessor {
    TestAccessor() {
      super()
    }
  }

  def subject = new TestAccessor()

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "delete throws"() {
    when: subject.delete("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
