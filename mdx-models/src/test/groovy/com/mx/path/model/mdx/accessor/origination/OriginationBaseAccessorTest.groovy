package com.mx.path.model.mdx.accessor.origination

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class OriginationBaseAccessorTest extends Specification {
  class TestAccessor extends OriginationBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "start throws"() {
    when: subject.start()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "answerChallenge throws"() {
    when: subject.answerChallenge("id", "challengeId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "authenticatedUserStart throws"() {
    when: subject.authenticatedUserStart("userId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "authenticatedUserAnswerChallenge throws"() {
    when: subject.authenticatedUserAnswerChallenge("userId", "id", "challengeId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
