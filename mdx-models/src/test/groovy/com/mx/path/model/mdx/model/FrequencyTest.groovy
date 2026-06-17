package com.mx.path.model.mdx.model

import spock.lang.Specification

class FrequencyTest extends Specification {
  def "default constructor"() {
    when:
    def subject = new Frequency()

    then:
    subject.getId() == null
    subject.getName() == null
    subject.getDescription() == null
  }

  def "parameterized constructor and getters"() {
    when:
    def subject = new Frequency("monthly", "Monthly", "Once per month")

    then:
    subject.getId() == "monthly"
    subject.getName() == "Monthly"
    subject.getDescription() == "Once per month"
  }

  def "setters"() {
    given:
    def subject = new Frequency()

    when:
    subject.setId("weekly")
    subject.setName("Weekly")
    subject.setDescription("Once per week")

    then:
    subject.getId() == "weekly"
    subject.getName() == "Weekly"
    subject.getDescription() == "Once per week"
  }
}
