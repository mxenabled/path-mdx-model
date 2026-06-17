package com.mx.path.model.mdx.accessor.credit_report

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class CreditReportSettingsBaseAccessorTest extends Specification {
  class TestAccessor extends CreditReportSettingsBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
