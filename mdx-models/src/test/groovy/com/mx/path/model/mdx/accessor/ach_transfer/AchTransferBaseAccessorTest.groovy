package com.mx.path.model.mdx.accessor.ach_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException
import com.mx.path.model.mdx.accessor.ach_transfer.ach_scheduled_transfer.AchScheduledTransferBaseAccessor

import spock.lang.Specification

class AchTransferBaseAccessorTest extends Specification {
  class TestAccessor extends AchTransferBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "cancel throws"() {
    when: subject.cancel("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accounts throws when not set"() {
    when: subject.accounts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accounts returns when set"() {
    given:
    def mock = Mock(AccountBaseAccessor)
    subject.setAccounts(mock)
    expect: subject.accounts() == mock
  }

  def "achAccounts throws when not set"() {
    when: subject.achAccounts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "achAccounts returns when set"() {
    given:
    def mock = Mock(AchAccountBaseAccessor)
    subject.setAchAccounts(mock)
    expect: subject.achAccounts() == mock
  }

  def "scheduled throws when not set"() {
    when: subject.scheduled()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "scheduled returns when set"() {
    given:
    def mock = Mock(AchScheduledTransferBaseAccessor)
    subject.setScheduled(mock)
    expect: subject.scheduled() == mock
  }
}
