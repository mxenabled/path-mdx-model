package com.mx.path.model.mdx.model.ondemand

import com.mx.path.model.mdx.model.account.Account

import spock.lang.Specification

import tools.jackson.core.JsonParser
import tools.jackson.dataformat.xml.XmlMapper

class MdxOnDemandDeserializerTest extends Specification {
  MdxOnDemandDeserializer<Account> subject
  void setup() {
    subject = new MdxOnDemandDeserializer<>(Account, "/account")
  }

  def "deserializes"() {
    given:
    XmlMapper mapper = new XmlMapper()
    JsonParser parser = mapper.createParser("<mdx><account><balance>0.09</balance><id>A-123</id><name>Checking</name></account></mdx>")

    when:
    def account = subject.deserialize(parser, null)

    then:
    account.getId() == "A-123"
    account.getBalance() == 0.09
  }
}
