package com.mx.path.model.mdx.accessor.cross_account_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class CrossAccountTransferBaseAccessorTest extends Specification {
  class TestAccessor extends CrossAccountTransferBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "accounts throws"() {
    when: subject.accounts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accountTypes throws"() {
    when: subject.accountTypes()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "create throws"() {
    when: subject.create(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "delete throws"() {
    when: subject.delete("id")
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

  def "fees throws when not set"() {
    when: subject.fees()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "fees returns when set"() {
    given:
    def mock = Mock(FeeBaseAccessor)
    subject.setFees(mock)
    expect: subject.fees() == mock
  }

  def "crossAccountRecurring returns null when not set"() {
    expect: subject.crossAccountRecurring() == null
  }

  def "crossAccountRecurring returns when set"() {
    given:
    def mock = Mock(CrossAccountRecurringTransferBaseAccessor)
    subject.setCrossAccountRecurring(mock)
    expect: subject.crossAccountRecurring() == mock
  }

  def "destination returns null when not set"() {
    expect: subject.destination() == null
  }

  def "destination returns when set"() {
    given:
    def mock = Mock(DestinationBaseAccessor)
    subject.setDestination(mock)
    expect: subject.destination() == mock
  }

  def "frequency returns null when not set"() {
    expect: subject.frequency() == null
  }

  def "frequency returns when set"() {
    given:
    def mock = Mock(FrequencyBaseAccessor)
    subject.setFrequency(mock)
    expect: subject.frequency() == mock
  }
}
