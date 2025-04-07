package com.mx.path.model.mdx.web

import spock.lang.Specification

class MdxSerializerFactoryBeanTest extends Specification {
  MdxSerializerFactoryBean subject

  def setup() {
    subject = new MdxSerializerFactoryBean()
  }

  def "testGetsGsonSerializer"() {
    expect:
    subject.getObject() != null
  }

  def "testIsNotSingleton"() {
    expect:
    !subject.isSingleton()
  }
}
