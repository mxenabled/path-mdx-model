package com.mx.path.model.mdx.web

import org.springframework.http.converter.ByteArrayHttpMessageConverter
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.ResourceHttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.http.converter.xml.JacksonXmlHttpMessageConverter

import spock.lang.Specification

import tools.jackson.databind.module.SimpleModule

class MdxOnDemandSerializationWebMvcConfigurerTest extends Specification {

  MdxOnDemandSerializationWebMvcConfigurer subject

  def setup() {
    subject = new MdxOnDemandSerializationWebMvcConfigurer()
  }

  def "filters non-whitelisted message converters and appends the custom XML converter"() {
    given:
    def gsonConverter = new GsonHttpMessageConverter()
    def stringConverter = new StringHttpMessageConverter()
    def byteArrayConverter = new ByteArrayHttpMessageConverter()
    def unwantedResourceConverter = new ResourceHttpMessageConverter()
    def unwantedFormConverter = new FormHttpMessageConverter()

    def converters = [
      unwantedResourceConverter,
      gsonConverter,
      unwantedFormConverter,
      stringConverter,
      byteArrayConverter
    ]

    when:
    subject.configureMessageConverters(converters)

    then:
    verifyAll(converters) {
      it.size() == 4
      it.contains(gsonConverter)
      it.contains(stringConverter)
      it.contains(byteArrayConverter)
      !it.contains(unwantedResourceConverter)
      !it.contains(unwantedFormConverter)
      it.last() instanceof JacksonXmlHttpMessageConverter
    }
  }

  def "payloadModule returns a correctly instantiated SimpleModule"() {
    when:
    def module = subject.payloadModule()

    then:
    module instanceof SimpleModule
  }
}
