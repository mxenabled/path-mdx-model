package com.mx.path.service.connection.realtime


import spock.lang.Specification

class MdxRealtimeConnectionTest extends Specification {
  MdxRealtimeConnection subject;

  def setup() {
    subject = new MdxRealtimeConnection(new MdxRealtimeConnectionConfiguration().tap {
      setClientId("clientId")
      setApiKey("apiKey")
    }).tap {
      setBaseUrl("baseUrl")
    }
  }

  def "accounts"() {
    when:
    def result = subject.accounts()

    then:
    noExceptionThrown()
    result != null
    result instanceof MdxRealtimeAccountsConnection
  }

  def "members"() {
    when:
    def result = subject.members()

    then:
    noExceptionThrown()
    result != null
    result instanceof MdxRealtimeMembersConnection
  }

  def "users"() {
    when:
    def result = subject.users()

    then:
    noExceptionThrown()
    result != null
    result instanceof MdxRealtimeUsersConnection
  }
}