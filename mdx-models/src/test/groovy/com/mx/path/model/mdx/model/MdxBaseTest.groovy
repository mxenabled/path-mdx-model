package com.mx.path.model.mdx.model

import com.mx.path.core.common.model.Warning
import com.mx.path.model.mdx.model.account.Account
import com.mx.testing.MdxBaseNested

import spock.lang.Specification

class MdxBaseTest extends Specification {
  def subject

  def setup() {
    subject = new Account()
    subject.appendWarning(new Warning("Some warning"))
    subject.appendWarning(new Warning("Another warning"))
  }

  def "getObjectMetadata"() {
    when:
    def metadata = subject.getObjectMetadata()

    then:
    metadata != null
  }

  def "getWarnings"() {
    when:
    def warnings = subject.getWarnings()

    then:
    warnings.size() == 2
  }

  def "setUserId"() {
    given:
    def nestedSubject = new MdxBaseNested()

    when:
    subject.setUserId("123")
    nestedSubject.setUserId("123")

    then:
    subject.getUserId() == "123"
    nestedSubject.getUserId() == null
  }
}
