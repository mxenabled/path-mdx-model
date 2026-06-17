package com.mx.path.model.mdx.accessor.document

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class DocumentBaseAccessorTest extends Specification {
  class TestAccessor extends DocumentBaseAccessor {
    TestAccessor() {
      super(null)
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("id")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "deliveryPreferences throws"() {
    when: subject.deliveryPreferences(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updateDeliveryPreferences throws"() {
    when: subject.updateDeliveryPreferences(null)
    then: thrown(AccessorMethodNotImplementedException)
  }
}
