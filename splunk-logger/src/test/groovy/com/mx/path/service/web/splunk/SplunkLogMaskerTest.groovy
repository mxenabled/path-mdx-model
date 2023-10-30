package com.mx.path.service.web.splunk

import com.mx.path.service.splunk.SplunkLogMasker

import spock.lang.*

class SplunkLogMaskerTest extends Specification {

  SplunkLogMasker subject

  def setup() {
    subject = new SplunkLogMasker()
  }

  def cleanup() {
    SplunkLogMasker.resetPatterns()
  }

  def "keepsNonSensitiveHeaderValue"() {
    expect:
    "cares" == subject.maskHeaderValue("nobody", "cares")
  }

  def "replacesSensitiveHeaderValues"() {
    expect:
    "**MASKED**" == subject.maskHeaderValue("x-csrf-token", "superSensitive")
    "**MASKED**" == subject.maskHeaderValue("X-CSRF-TOKEN", "superSensitive")
  }

  def "replaceSensitiveHeaderValueWhenHeaderIsCooke"() {
    expect:
    "ASP.Net_SessionId=**MASKED**;" == subject.maskHeaderValue("Cookie", "ASP.Net_SessionId=superSensitive;")
  }

  def "masksAntiForgeryTokenInJsonStringBody"() {
    given:
    def json = "{`antiForgeryToken`:`sensitive`,`user_id`:`bob`}".replace('`', '"')

    expect:
    "{`antiForgeryToken`:`**MASKED**`,`user_id`:`bob`}".replace('`', '"') == subject.maskPayload(json)
  }

  def "masksPasswordInJsonStringBody"() {
    given:
    def json = "{`authentication`:{`username`:`frank`,`password`:`\$up3r\$3cr4t`}}".replace('`', '"');

    expect:
    "{`authentication`:{`username`:`frank`,`password`:`**MASKED**`}}".replace('`', '"') == subject.maskPayload(json)
  }

  def "masksMultipleOccurrencesInJsonStringBody"() {
    given:
    def json = "{`token`:`SecretStuff`,`token`:`\$up3r\$3cr4t`}".replace('`', '"')

    expect:
    "{`token`:`**MASKED**`,`token`:`**MASKED**`}".replace('`', '"') == subject.maskPayload(json)
  }

  def "registerPayloadPattern"() {
    given:
    SplunkLogMasker.registerPayloadPattern("(Secret)")

    expect:
    "Some**MASKED**Stuff" == subject.maskPayload("SomeSecretStuff")
  }

  def "registerHeaderKey"() {
    given:
    SplunkLogMasker.registerHeaderKey("secret")

    expect:
    "**MASKED**" == subject.maskHeaderValue("secret", "superSensitive")
  }

  def "registerCookieKey"() {
    given:
    SplunkLogMasker.registerCookieKey("secret")

    expect:
    "secret=**MASKED**" == subject.maskHeaderValue("Cookie", "secret=superSensitive")
  }

  def "registerJsonPayloadPattern"() {
    given:
    SplunkLogMasker.registerPayloadPattern("\"password\":\"(.+)\"")

    expect:
    "\"password\":\"**MASKED**\"" == subject.maskPayload("\"password\":\"SOME_PASSWORD\"")
  }

  def "registerJsonPayloadPatternWithSpaces"() {
    given:
    SplunkLogMasker.registerPayloadPattern("\"password\" : \"(.+)\"")

    expect:
    "\"password\" : \"**MASKED**\"" == subject.maskPayload("\"password\" : \"SOME_PASSWORD\"")
  }
}
