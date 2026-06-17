package com.mx.path.model.mdx.accessor.p2p_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class P2PTransferBaseAccessorTest extends Specification {
  def subject = new P2PTransferBaseAccessor()

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "cancel throws"() {
    when: subject.cancel("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "sources returns null when not set"() {
    expect: subject.sources() == null
  }

  def "sources returns when set"() {
    given:
    def mock = Mock(SourceBaseAccessor)
    subject.setSources(mock)
    expect: subject.sources() == mock
  }

  def "recurring returns null when not set"() {
    expect: subject.recurring() == null
  }

  def "recurring returns when set"() {
    given:
    def mock = Mock(RecurringP2PTransferBaseAccessor)
    subject.setRecurring(mock)
    expect: subject.recurring() == mock
  }

  def "recipients returns null when not set"() {
    expect: subject.recipients() == null
  }

  def "recipients returns when set"() {
    given:
    def mock = Mock(RecipientBaseAccessor)
    subject.setRecipients(mock)
    expect: subject.recipients() == mock
  }
}
