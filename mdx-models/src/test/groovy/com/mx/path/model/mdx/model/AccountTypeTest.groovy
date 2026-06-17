package com.mx.path.model.mdx.model

import spock.lang.Specification

class AccountTypeTest extends Specification {
  def "getters and setters"() {
    given:
    def subject = new AccountType()

    when:
    subject.setId("id1")
    subject.setName("checking")
    subject.setIsNumberRequired(true)

    then:
    subject.getId() == "id1"
    subject.getName() == "checking"
    subject.getIsNumberRequired() == true
  }
}
