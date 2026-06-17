package com.mx.path.model.mdx.accessor.transfer

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException
import com.mx.path.model.mdx.accessor.transfer.recurring_transfer.RecurringTransferBaseAccessor

import spock.lang.Specification

class TransferBaseAccessorTest extends Specification {
  class TestAccessor extends TransferBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

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

  def "cancel throws"() {
    when: subject.cancel("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "start throws"() {
    when: subject.start(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "finish throws"() {
    when: subject.finish("id")
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

  def "recurring throws when not set"() {
    when: subject.recurring()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "recurring returns when set"() {
    given:
    def mock = Mock(RecurringTransferBaseAccessor)
    subject.setRecurring(mock)
    expect: subject.recurring() == mock
  }

  def "transferAmountOptions throws when not set"() {
    when: subject.transferAmountOptions()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "transferAmountOptions returns when set"() {
    given:
    def mock = Mock(AmountOptionBaseAccessor)
    subject.setTransferAmountOptions(mock)
    expect: subject.transferAmountOptions() == mock
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
