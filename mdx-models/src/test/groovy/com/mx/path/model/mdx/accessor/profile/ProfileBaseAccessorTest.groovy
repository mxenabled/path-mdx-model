package com.mx.path.model.mdx.accessor.profile

import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException

import spock.lang.Specification

class ProfileBaseAccessorTest extends Specification {
  def subject = new ProfileBaseAccessor()

  def "get throws"() {
    when: subject.get()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "update throws"() {
    when: subject.update(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updateUserName throws"() {
    when: subject.updateUserName(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updatePassword throws"() {
    when: subject.updatePassword(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updatePasswordResume throws"() {
    when: subject.updatePasswordResume("challengeId", null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updateUserNameWithMFA throws"() {
    when: subject.updateUserNameWithMFA(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "updatePasswordWithMFA throws"() {
    when: subject.updatePasswordWithMFA(null)
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "addresses throws when not set"() {
    when: subject.addresses()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "addresses returns when set"() {
    given:
    def mock = Mock(AddressBaseAccessor)
    subject.setAddresses(mock)
    expect: subject.addresses() == mock
  }

  def "challengeQuestions throws when not set"() {
    when: subject.challengeQuestions()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "challengeQuestions returns when set"() {
    given:
    def mock = Mock(ChallengeQuestionBaseAccessor)
    subject.setChallengeQuestions(mock)
    expect: subject.challengeQuestions() == mock
  }

  def "securityQuestions throws when not set"() {
    when: subject.securityQuestions()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "securityQuestions returns when set"() {
    given:
    def mock = Mock(SecurityQuestionBaseAccessor)
    subject.setSecurityQuestions(mock)
    expect: subject.securityQuestions() == mock
  }

  def "emails throws when not set"() {
    when: subject.emails()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "emails returns when set"() {
    given:
    def mock = Mock(EmailBaseAccessor)
    subject.setEmails(mock)
    expect: subject.emails() == mock
  }

  def "phones throws when not set"() {
    when: subject.phones()
    then: thrown(AccessorMethodNotImplementedException)
  }

  def "phones returns when set"() {
    given:
    def mock = Mock(PhoneBaseAccessor)
    subject.setPhones(mock)
    expect: subject.phones() == mock
  }
}
