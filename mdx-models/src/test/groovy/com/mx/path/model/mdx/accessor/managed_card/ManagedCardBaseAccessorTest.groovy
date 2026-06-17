package com.mx.path.model.mdx.accessor.managed_card

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class ManagedCardBaseAccessorTest extends Specification {
  class TestAccessor extends ManagedCardBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "activate throws"() {
    when: subject.activate("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "enable throws"() {
    when: subject.enable("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "getUnmaskedCardNumber throws"() {
    when: subject.getUnmaskedCardNumber("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "getCvv throws"() {
    when: subject.getCvv("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "pause throws"() {
    when: subject.pause("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "replace throws"() {
    when: subject.replace("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "setPin throws"() {
    when: subject.setPin("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updateNotificationPreferences throws"() {
    when: subject.updateNotificationPreferences("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "alerts returns null when not set"() {
    expect: subject.alerts() == null
  }

  def "alerts returns when set"() {
    given:
    def mock = Mock(ManagedCardAlertBaseAccessor)
    subject.setAlerts(mock)
    expect: subject.alerts() == mock
  }
}
