package com.mx.path.model.mdx.accessor.device

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class DeviceBaseAccessorTest extends Specification {
  class TestAccessor extends DeviceBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "verificationMethods throws when not set"() {
    when: subject.verificationMethods()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "verificationMethods returns when set"() {
    given:
    def mock = Mock(VerificationMethodsBaseAccessor)
    subject.setVerificationMethods(mock)
    expect: subject.verificationMethods() == mock
  }
}
