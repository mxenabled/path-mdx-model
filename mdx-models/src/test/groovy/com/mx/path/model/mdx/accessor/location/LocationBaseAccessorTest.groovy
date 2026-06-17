package com.mx.path.model.mdx.accessor.location

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class LocationBaseAccessorTest extends Specification {
  class TestAccessor extends LocationBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "search throws"() {
    when: subject.search(null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
