package com.mx.path.model.mdx.web

import com.google.gson.Gson
import com.mx.models.id.Authentication

import spock.lang.Specification

class MdxSerializerTest extends Specification {

  Gson subject
  def json = "{`authentication`:{`login`: `bob`,`password`: `Pa\$\$w0rd`}}".replace("`", "\"")
  def password = "Pa\$\$w0rd"

  def setup() {
    subject = new MdxSerializerFactoryBean().getObject()
  }

  def "testDeserializesAuthentication"() {
    given:
    Authentication result = subject.fromJson(json, Authentication.class)

    expect:
    "bob" == result.getLogin()
    password.toCharArray() == result.getPassword()
  }

  def "testSerializesAuthentication"() {
    given:
    def expected = "{`login`: `bob`,`password`: `Pa\$\$w0rd`}".replace("`", "\"")
    def auth = new Authentication()
    auth.setLogin("bob")
    auth.setPassword(password.toCharArray())

    expect:
    subject.toJson(auth).replace("\n", "").replace("  ", "") == expected
  }
}
