package com.mx.path.model.mdx.model

import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

import spock.lang.Specification

class CharArrayAdapterTest extends Specification {
  def subject = new CharArrayAdapter()

  def "write null value"() {
    given:
    def writer = Mock(JsonWriter)

    when:
    subject.write(writer, null)

    then:
    1 * writer.nullValue()
  }

  def "write char array"() {
    given:
    def writer = Mock(JsonWriter)

    when:
    subject.write(writer, ['h', 'i'] as char[])

    then:
    1 * writer.value("hi")
  }

  def "read null token returns empty array"() {
    given:
    def reader = Mock(JsonReader) {
      peek() >> JsonToken.NULL
    }

    when:
    def result = subject.read(reader)

    then:
    1 * reader.nextNull()
    result.length == 0
  }

  def "read string value"() {
    given:
    def reader = Mock(JsonReader) {
      peek() >> JsonToken.STRING
      hasNext() >> true
      nextString() >> "hello"
    }

    when:
    def result = subject.read(reader)

    then:
    result == 'hello'.toCharArray()
  }

  def "read with no next returns empty array"() {
    given:
    def reader = Mock(JsonReader) {
      peek() >> JsonToken.STRING
      hasNext() >> false
    }

    when:
    def result = subject.read(reader)

    then:
    result.length == 0
  }
}
