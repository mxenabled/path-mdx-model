package com.mx.path.model.mdx.accessor.id

import com.mx.path.core.common.accessor.PathResponseStatus

import spock.lang.Specification

class IdBaseAccessorTest extends Specification {
  def subject

  def setup() {
    subject = new TestAccessor()
  }

  class TestAccessor extends IdBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def "test logout"() {
    when:
    def response = subject.logout(null)
    then:
    response.status == PathResponseStatus.NO_CONTENT
  }
}
