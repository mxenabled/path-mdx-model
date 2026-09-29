package com.mx.path.model.mdx.model.products

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.mx.path.model.mdx.model.Resources

import spock.lang.Specification

class ProductTest extends Specification {
  def gson

  def setup() {
    // Mirrors MdxSerializerFactoryBean, which is how products are serialized in production.
    def builder = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setDateFormat("YYYY-MM-dd")
        .setPrettyPrinting()
    Resources.registerResources(builder)
    gson = builder.create()
  }

  def "serializes Rev 6 fields with the names the spec uses"() {
    given:
    def subject = new Product()
    subject.setId("133")
    subject.setStatus("ACTIVATING")
    def accountData = new AccountData()
    accountData.setAccountType("CHECKING")
    subject.setAccountData(accountData)

    when:
    def json = JsonParser.parseString(gson.toJson(subject)).getAsJsonObject()

    then:
    json.get("status").getAsString() == "ACTIVATING"
    json.getAsJsonObject("account_data").get("account_type").getAsString() == "CHECKING"
  }

  def "omits Rev 6 fields entirely when they are null"() {
    given: "a Rev 5 shaped product, as CPB produces today"
    def subject = new Product()
    subject.setId("133")
    subject.setName("Exceptional Checking")
    subject.setIsSelected(true)

    when:
    def json = JsonParser.parseString(gson.toJson(subject)).getAsJsonObject()

    then: "moneymobilex sees byte-identical output to before Rev 6"
    !json.has("status")
    !json.has("account_data")
    json.get("is_selected").getAsBoolean()
  }

  def "deserializes a Rev 5 body that carries is_selected"() {
    given:
    def json = '{"id":"133","name":"Exceptional Checking","is_selected":true}'

    when:
    def subject = gson.fromJson(json, Product)

    then:
    subject.getId() == "133"
    subject.getIsSelected()
    subject.getStatus() == null
    subject.getAccountData() == null
  }

  def "deserializes a Rev 6 body"() {
    given:
    def json = '{"id":"133","status":"COMPLETE","account_data":{"account_type":"CHECKING"}}'

    when:
    def subject = gson.fromJson(json, Product)

    then:
    subject.getStatus() == "COMPLETE"
    subject.getAccountData().getAccountType() == "CHECKING"
  }

  def "round trips account_data"() {
    given:
    def subject = new Product()
    def accountData = new AccountData()
    accountData.setAccountType("MORTGAGE")
    subject.setAccountData(accountData)

    when:
    def result = gson.fromJson(gson.toJson(subject), Product)

    then:
    result.getAccountData().getAccountType() == "MORTGAGE"
  }
}
