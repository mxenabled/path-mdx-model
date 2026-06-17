package com.mx.path.model.mdx.model

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.mx.path.model.mdx.model.account.Account

import spock.lang.Specification

class ModelWrappableSerializerTest extends Specification {
  def subject = new ModelWrappableSerializer("account")

  def "deserialize unwrapped object"() {
    given:
    def gson = new Gson()
    def json = gson.toJsonTree([id: "123"])
    def context = Mock(com.google.gson.JsonDeserializationContext) {
      deserialize(_, _) >> new Account()
    }

    when:
    def result = subject.deserialize(json, Account, context)

    then:
    result != null
  }

  def "deserialize wrapped object"() {
    given:
    def gson = new Gson()
    def json = gson.toJsonTree([account: [id: "123"]])
    def context = Mock(com.google.gson.JsonDeserializationContext) {
      deserialize(_, _) >> new Account()
    }

    when:
    def result = subject.deserialize(json, Account, context)

    then:
    result != null
  }

  def "deserialize non-object throws"() {
    given:
    def json = new JsonPrimitive("not-an-object")

    when:
    subject.deserialize(json, Account, null)

    then:
    thrown(JsonParseException)
  }

  def "serialize unwrapped"() {
    given:
    def account = new Account()
    account.setId("123")

    when:
    def result = subject.serialize(account, Account, null)

    then:
    result.isJsonObject()
    result.asJsonObject.has("id")
  }

  def "serialize wrapped"() {
    given:
    def account = new Account()
    account.setId("123")
    account.wrapped = true

    when:
    def result = subject.serialize(account, Account, null)

    then:
    result.isJsonObject()
    result.asJsonObject.has("account")
  }
}
