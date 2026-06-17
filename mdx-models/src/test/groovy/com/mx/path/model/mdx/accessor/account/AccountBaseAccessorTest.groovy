package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class AccountBaseAccessorTest extends Specification {
  class TestAccessor extends AccountBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "get throws"() {
    when: subject.get("id")
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

  def "update throws"() {
    when: subject.update("id", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accountAlerts throws when not set"() {
    when: subject.accountAlerts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accountAlerts returns when set"() {
    given:
    def mock = Mock(AccountAlertBaseAccessor)
    subject.setAccountAlerts(mock)
    expect: subject.accountAlerts() == mock
  }

  def "accountDetails throws when not set"() {
    when: subject.accountDetails()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accountDetails returns when set"() {
    given:
    def mock = Mock(AccountDetailsBaseAccessor)
    subject.setAccountDetails(mock)
    expect: subject.accountDetails() == mock
  }

  def "accountNumbers throws when not set"() {
    when: subject.accountNumbers()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accountNumbers returns when set"() {
    given:
    def mock = Mock(AccountNumbersBaseAccessor)
    subject.setAccountNumbers(mock)
    expect: subject.accountNumbers() == mock
  }

  def "accountOverdrafts throws when not set"() {
    when: subject.accountOverdrafts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accountOverdrafts returns when set"() {
    given:
    def mock = Mock(AccountOverdraftBaseAccessor)
    subject.setAccountOverdrafts(mock)
    expect: subject.accountOverdrafts() == mock
  }

  def "accountOwners throws when not set"() {
    when: subject.accountOwners()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accountOwners returns when set"() {
    given:
    def mock = Mock(AccountOwnerBaseAccessor)
    subject.setAccountOwners(mock)
    expect: subject.accountOwners() == mock
  }

  def "accountStopPayments throws when not set"() {
    when: subject.accountStopPayments()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accountStopPayments returns when set"() {
    given:
    def mock = Mock(AccountStopPaymentsBaseAccessor)
    subject.setAccountStopPayments(mock)
    expect: subject.accountStopPayments() == mock
  }

  def "addresses throws when not set"() {
    when: subject.addresses()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "addresses returns when set"() {
    given:
    def mock = Mock(AccountAddressBaseAccessor)
    subject.setAddresses(mock)
    expect: subject.addresses() == mock
  }

  def "transactions throws when not set"() {
    when: subject.transactions()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "transactions returns when set"() {
    given:
    def mock = Mock(TransactionBaseAccessor)
    subject.setTransactions(mock)
    expect: subject.transactions() == mock
  }

  def "repayments throws when not set"() {
    when: subject.repayments()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "repayments returns when set"() {
    given:
    def mock = Mock(RepaymentBaseAccessor)
    subject.setRepayments(mock)
    expect: subject.repayments() == mock
  }
}
