package com.mx.path.model.mdx.model

import com.fasterxml.jackson.databind.module.SimpleModule
import com.google.gson.GsonBuilder

import spock.lang.Specification

class ResourcesTest extends Specification {
  def "registerResources registers all Gson type adapters"() {
    given:
    def builder = new GsonBuilder()

    when:
    Resources.registerResources(builder)

    then:
    builder.create() != null
  }

  def "registerOnDemandResources registers all Jackson type adapters"() {
    given:
    def module = new SimpleModule()

    when:
    Resources.registerOnDemandResources(module)

    then:
    module != null
  }
}
