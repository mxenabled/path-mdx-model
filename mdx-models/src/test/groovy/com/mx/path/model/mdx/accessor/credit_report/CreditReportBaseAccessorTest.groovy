package com.mx.path.model.mdx.accessor.credit_report

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class CreditReportBaseAccessorTest extends Specification {
  class TestAccessor extends CreditReportBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("reportId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "subscribe throws"() {
    when: subject.subscribe()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "unsubscribe throws"() {
    when: subject.unsubscribe()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "scoreFactors throws when not set"() {
    when: subject.scoreFactors()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "scoreFactors returns when set"() {
    given:
    def mock = Mock(ScoreFactorBaseAccessor)
    subject.setScoreFactors(mock)
    expect: subject.scoreFactors() == mock
  }

  def "settings throws when not set"() {
    when: subject.settings()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "settings returns when set"() {
    given:
    def mock = Mock(CreditReportSettingsBaseAccessor)
    subject.setSettings(mock)
    expect: subject.settings() == mock
  }
}
