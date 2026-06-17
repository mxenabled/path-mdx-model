package com.mx.path.model.mdx.accessor.account

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class TransactionBaseAccessorTest extends Specification {
  class TestAccessor extends TransactionBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "recent throws"() {
    when: subject.recent("accountId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list("accountId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "search throws"() {
    when: subject.search("accountId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "checkImages throws when not set"() {
    when: subject.checkImages()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "checkImages returns when set"() {
    given:
    def mock = Mock(CheckImageBaseAccessor)
    subject.setCheckImages(mock)
    expect: subject.checkImages() == mock
  }

  def "disputes throws when not set"() {
    when: subject.disputes()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "disputes returns when set"() {
    given:
    def mock = Mock(DisputeBaseAccessor)
    subject.setDisputes(mock)
    expect: subject.disputes() == mock
  }
}
