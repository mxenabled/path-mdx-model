package com.mx.path.model.mdx.model

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonParser
import com.google.gson.JsonSerializationContext
import com.google.gson.reflect.TypeToken
import com.mx.path.model.mdx.model.account.Account
import spock.lang.Specification

import static org.mockito.Mockito.mock

class MdxWrappableSerializerTest extends Specification {
  def subject

  def "deserialize object"() {
    given:
    subject = new ModelWrappableSerializer("Account")
    def jsonElement = JsonParser.parseString("{\"Account\":{\"id\":\"1234\"}}")

    when:
    Account result = subject.deserialize(jsonElement, Account, mock(JsonDeserializationContext)) as Account

    then:
    result.id == "1234"
  }

  def "deserialize array"() {
    given:
    subject = new ModelWrappableSerializer("Accounts")
    def jsonArray = (JsonArray) JsonParser.parseString("[{\"id\":\"1234\"},{\"id\":\"5678\"}]")

    when:
    MdxList<Account> result = subject.deserialize(jsonArray, new TypeToken<MdxList<Account>>(){}.getType(), mock(JsonDeserializationContext)) as MdxList<Account>

    then:
    result.size() == 2
    result[0].id == "1234"
    result[1].id == "5678"
  }

  def "serialize wrapped"() {
    given:
    subject = new ModelWrappableSerializer("Account")
    def account = new Account().tap {
      id = "1234"
      setWrapped(true)
    }

    when:
    def jsonElement = subject.serialize(account, Account, mock(JsonSerializationContext))

    then:
    jsonElement.toString() == "{\"Account\":{\"id\":\"1234\"}}"
  }

  def "serialize unwrapped"() {
    given:
    subject = new ModelWrappableSerializer("Account")
    def account = new Account().tap {
      id = "1234"
      setWrapped(false)
    }

    when:
    def jsonElement = subject.serialize(account, Account, mock(JsonSerializationContext))

    then:
    jsonElement.toString() == "{\"id\":\"1234\"}"
  }
}
