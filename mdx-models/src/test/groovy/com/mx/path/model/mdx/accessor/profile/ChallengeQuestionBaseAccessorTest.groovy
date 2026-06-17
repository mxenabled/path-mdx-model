package com.mx.path.model.mdx.accessor.profile

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class ChallengeQuestionBaseAccessorTest extends Specification {
  class TestAccessor extends ChallengeQuestionBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update(null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
