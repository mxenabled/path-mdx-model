package com.mx.path.model.mdx.accessor.managed_card

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class ManagedCardAlertBaseAccessorTest extends Specification {
  class TestAccessor extends ManagedCardAlertBaseAccessor {
    TestAccessor() {
      super()
    }
  }

  def subject = new TestAccessor()

  def "get throws"() {
    when: subject.get("cardId", "alertId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "list throws"() {
    when: subject.list("cardId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update("cardId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "deliveryMethods throws"() {
    when: subject.deliveryMethods("cardId", "alertId")
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "cards throws"() {
    when: subject.cards()
    then: thrown(AccessorMethodNotImplementedException)
  }
}
