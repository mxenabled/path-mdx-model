package com.mx.path.model.mdx.accessor.payment

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class PaymentBaseAccessorTest extends Specification {
  class TestAccessor extends PaymentBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "enrollment throws"() {
    when: subject.enrollment()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updateEnrollment throws"() {
    when: subject.updateEnrollment(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "settings throws"() {
    when: subject.settings()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updateSettings throws"() {
    when: subject.updateSettings(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "settings20260427 throws"() {
    when: subject.settings20260427()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updateSettings20260427 throws"() {
    when: subject.updateSettings20260427(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "accounts throws"() {
    when: subject.accounts()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "create throws"() {
    when: subject.create(null)
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

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "bills throws when not set"() {
    when: subject.bills()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "bills returns when set"() {
    given:
    def mock = Mock(BillBaseAccessor)
    subject.setBills(mock)
    expect: subject.bills() == mock
  }

  def "merchants throws when not set"() {
    when: subject.merchants()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "merchants returns when set"() {
    given:
    def mock = Mock(MerchantBaseAccessor)
    subject.setMerchants(mock)
    expect: subject.merchants() == mock
  }

  def "payees throws when not set"() {
    when: subject.payees()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "payees returns when set"() {
    given:
    def mock = Mock(PayeeBaseAccessor)
    subject.setPayees(mock)
    expect: subject.payees() == mock
  }

  def "recurring throws when not set"() {
    when: subject.recurring()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "recurring returns when set"() {
    given:
    def mock = Mock(RecurringPaymentBaseAccessor)
    subject.setRecurring(mock)
    expect: subject.recurring() == mock
  }
}
