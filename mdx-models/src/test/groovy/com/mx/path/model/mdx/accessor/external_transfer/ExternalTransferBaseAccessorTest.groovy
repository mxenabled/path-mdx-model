package com.mx.path.model.mdx.accessor.external_transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class ExternalTransferBaseAccessorTest extends Specification {
  class TestAccessor extends ExternalTransferBaseAccessor {
    TestAccessor() {
      super()
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
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "child accessors return null when not set"() {
    expect:
    subject.accounts() == null
    subject.externalAccounts() == null
    subject.recurring() == null
  }

  def "child accessors return when set"() {
    given:
    def accounts = Mock(AccountBaseAccessor)
    def externalAccounts = Mock(ExternalAccountBaseAccessor)
    def recurring = Mock(RecurringExternalTransferBaseAccessor)
    subject.setAccounts(accounts)
    subject.setExternalAccounts(externalAccounts)
    subject.setRecurring(recurring)

    expect:
    subject.accounts() == accounts
    subject.externalAccounts() == externalAccounts
    subject.recurring() == recurring
  }
}
