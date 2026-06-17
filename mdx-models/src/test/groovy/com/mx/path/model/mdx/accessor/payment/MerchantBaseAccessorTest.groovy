package com.mx.path.model.mdx.accessor.payment

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class MerchantBaseAccessorTest extends Specification {
  class TestAccessor extends MerchantBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "list throws"() {
    when: subject.list("name")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "search throws"() {
    when: subject.search("merchantCategoryId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "categories throws"() {
    when: subject.categories()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
