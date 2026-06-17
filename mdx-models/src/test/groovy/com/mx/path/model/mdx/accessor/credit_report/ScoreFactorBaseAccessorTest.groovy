package com.mx.path.model.mdx.accessor.credit_report

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class ScoreFactorBaseAccessorTest extends Specification {
  class TestAccessor extends ScoreFactorBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("reportId", "factorId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list("reportId")
    then: thrown(AccessorMethodNotImplementedException)
  }
}
