package com.mx.web.mdx.models

import java.time.LocalDate

import spock.lang.Specification

class DocumentSearchTest extends Specification {

  def endOn = LocalDate.now().minusMonths(1)
  def startOn = LocalDate.now().minusMonths(2)
  def type = "ACCOUNT_STATEMENT"
  def accountId = "ACCOUNT-1234"

  def "DocumentSearch builds as expected"() {
    given:
    def subject = new DocumentSearch()
    subject.setEnd_on(endOn)
    subject.setStart_on(startOn)
    subject.setType(type)
    subject.setAccount_id(accountId)

    expect:
    subject.getEnd_on() == endOn
    subject.getStart_on() == startOn
    subject.getType() == type
    subject.getAccount_id() == accountId
  }
}
