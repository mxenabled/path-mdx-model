package com.mx.path.model.mdx.model.ondemand

import static com.mx.path.extensions.StringStaticExtension.sanitizeXml

import java.time.LocalDate

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.account.Account
import com.mx.path.model.mdx.model.account.OnDemandAccounts
import com.mx.path.model.mdx.model.account.Transaction
import com.mx.path.model.mdx.model.ondemand.mixins.AccountXmlMixin
import com.mx.path.model.mdx.model.ondemand.mixins.MixinDefinition
import com.mx.path.model.mdx.model.ondemand.mixins.OnDemandAccountsXmlMixin
import com.mx.path.testing.WithMockery

import spock.lang.Specification

class MdxOnDemandSerializerTest extends Specification implements WithMockery {
  MdxOnDemandSerializer subject
  ToXmlGenerator generator
  StringWriter stringWriter

  void setup() {
    subject = new MdxOnDemandSerializer()
    stringWriter = new StringWriter()
    generator = new XmlFactory().createGenerator(stringWriter)
  }

  def cleanup() {
    // Verify that exceptions won't be thrown when this is called
    generator.close()
  }

  def "unwrapped, interacts with generator"() {
    given:
    def account = new Account().tap {
      id = "A-123"
      name = "Checking"
      balance = 0.09
      fullAccountNumber = "DONT_RENDER_ME"
    }

    when:
    subject.serialize(account, (JsonGenerator) generator, (SerializerProvider) null)
    generator.flush()

    then:
    def expectedResponse = "<Account>\n" +
        "  <balance>0.09</balance>\n" +
        "  <id>A-123</id>\n" +
        "  <name>Checking</name>\n" +
        "  <wrapped>false</wrapped>\n" +
        "</Account>\n"

    sanitizeXml(stringWriter) == sanitizeXml(expectedResponse)
  }

  def "wrapped, interacts with generator"() {
    given:
    def account = new Account().tap {
      id = "A-123"
      name = "Checking"
      balance = 0.09
      fullAccountNumber = "DONT_RENDER_ME"
    }

    when:
    subject.serialize(account.wrapped(), (JsonGenerator) generator, (SerializerProvider) null)
    generator.flush()

    then:
    def expectedResponse = "<mdx version=\"5.0\">\n" +
        "<Account>\n" +
        "  <balance>0.09</balance>\n" +
        "  <id>A-123</id>\n" +
        "  <name>Checking</name>\n" +
        "  <wrapped>true</wrapped>\n" +
        "</Account>\n" +
        "</mdx>\n"

    sanitizeXml(stringWriter) == sanitizeXml(expectedResponse)
  }

  def "applies mixins"() {
    given:
    subject = new MdxOnDemandSerializer(new MixinDefinition(Account, AccountXmlMixin))

    def account = new Account().tap {
      id = "A-123"
      name = "Checking"
      balance = 0.09
      fullAccountNumber = "DONT_RENDER_ME"
      paymentDueOn = LocalDate.of(2020, 12, 5)
    }

    when:
    subject.serialize(account.wrapped(), (JsonGenerator) generator, (SerializerProvider) null)
    generator.flush()

    then:
    def expectedResponse = "<mdx version=\"5.0\">\n" +
        "<account>\n" +
        "  <balance>0.09</balance>\n" +
        "  <id>A-123</id>\n" +
        "  <name>Checking</name>\n" +
        "  <payment_due_on>2020-12-05</payment_due_on>\n" +
        "</account>\n" +
        "</mdx>\n"

    sanitizeXml(stringWriter) == sanitizeXml(expectedResponse)
  }

  def "accounts list mixins"() {
    given:
    subject = new MdxOnDemandSerializer(
        new MixinDefinition(OnDemandAccounts, OnDemandAccountsXmlMixin),
        new MixinDefinition(Account, AccountXmlMixin))

    def accounts = new MdxList()
    accounts.add(new Account().tap {
      id = "A-123"
      name = "Checking"
      balance = 0.09
      fullAccountNumber = "DONT_RENDER_ME"
      paymentDueOn = LocalDate.of(2020, 12, 5)
    })

    accounts.add(new Account().tap {
      id = "A-321"
      name = "Savings"
      balance = 0.09
      fullAccountNumber = "DONT_RENDER_ME"
      paymentDueOn = LocalDate.of(2020, 12, 5)
    })
    def onDemandAccounts = new OnDemandAccounts(accounts)

    when:
    subject.serialize(onDemandAccounts.wrapped(), (JsonGenerator) generator, (SerializerProvider) null)
    generator.flush()

    then:
    def expectedResponse = "<mdx version=\"5.0\">\n" +
        "<accounts>\n" +
        "  <account>\n" +
        "    <balance>0.09</balance>\n" +
        "    <id>A-123</id>\n" +
        "    <name>Checking</name>\n" +
        "    <payment_due_on>2020-12-05</payment_due_on>\n" +
        "  </account>\n" +
        "  <account>\n" +
        "    <balance>0.09</balance>\n" +
        "    <id>A-321</id>\n" +
        "    <name>Savings</name>\n" +
        "    <payment_due_on>2020-12-05</payment_due_on>\n" +
        "  </account>\n" +
        "</accounts>\n" +
        "</mdx>\n"

    sanitizeXml(stringWriter) == sanitizeXml(expectedResponse)
  }

  def "empty accounts list mixins"() {
    given:
    subject = new MdxOnDemandSerializer(
        new MixinDefinition(OnDemandAccounts, OnDemandAccountsXmlMixin),
        new MixinDefinition(Account, AccountXmlMixin))

    def accounts = new MdxList()
    def onDemandAccounts = new OnDemandAccounts(accounts)

    when:
    subject.serialize(onDemandAccounts.wrapped(), (JsonGenerator) generator, (SerializerProvider) null)
    generator.flush()

    then:
    def expectedResponse = "<mdx version=\"5.0\">\n" +
        "<accounts/>\n" +
        "</mdx>\n"

    sanitizeXml(stringWriter) == sanitizeXml(expectedResponse)
  }

  def "serializes LocalDate to string"() {
    given:
    def transaction = new Transaction().tap {
      postedOn = LocalDate.of(2020, 01, 12)
    }

    when:
    subject.serialize(transaction, (JsonGenerator) generator, (SerializerProvider) null)
    generator.flush()

    then:
    def expectedResponse = "<Transaction>\n" +
        "  <posted_on>2020-01-12</posted_on>\n" +
        "  <wrapped>false</wrapped>\n" +
        "</Transaction>\n"

    sanitizeXml(stringWriter) == sanitizeXml(expectedResponse)
  }

  def "serializes large amounts"() {
    given:
    def account = new Account().tap {
      id = "A-123"
      name = "Checking"
      balance = 30000000.00
      fullAccountNumber = "DONT_RENDER_ME"
    }

    when:
    print("BALANCE: "+account.balance)
    subject.serialize(account.wrapped(), (JsonGenerator) generator, (SerializerProvider) null)
    generator.flush()

    then:
    def expectedResponse = "<mdx version=\"5.0\">\n" +
        "<Account>\n" +
        "  <balance>30000000.00</balance>\n" +
        "  <id>A-123</id>\n" +
        "  <name>Checking</name>\n" +
        "  <wrapped>true</wrapped>\n" +
        "</Account>\n" +
        "</mdx>\n"

    sanitizeXml(stringWriter) == sanitizeXml(expectedResponse)
  }
}
