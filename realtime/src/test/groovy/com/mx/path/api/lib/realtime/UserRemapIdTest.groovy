package com.mx.path.api.lib.realtime

import com.google.gson.Gson
import com.mx.common.accessors.UnauthorizedException
import com.mx.common.http.HttpStatus
import com.mx.path.api.lib.realtime.models.MdxUserWrapper
import com.mx.path.gateway.net.Response

import spock.lang.Specification

@Deprecated
class UserRemapIdTest extends Specification {
  Gson gson = new Gson()
  def newUserId = "new_user_id"
  def request
  def userId = "user_id"

  def setup() {
    request = new UserRemapIdRequest("base_url", "client_id", "api_key", userId, newUserId)
  }

  def "Request initialization test"() {
    expect:
    request.featureName == "id"
    request.baseUrl == "base_url"
    request.path == "/client_id/users/${userId}/remap_id.json"
    request.method == "PUT"
    request.getHeaders().get("MD-API-KEY") == "api_key"
    request.getHeaders().get("Content-Type") == "application/vnd.moneydesktop.mdx.v5+json"
    request.getHeaders().get("Accept") == "application/vnd.moneydesktop.mdx.v5+json"
    def user = gson.fromJson((String) request.getBody(), MdxUserWrapper)
    user.user.id == newUserId
  }

  def "onComplete callback, 200 -> no exception"() {
    given:
    def response = new Response()
    response.withStatus(HttpStatus.OK)

    when:
    request.completed(response)

    then:
    noExceptionThrown()
  }

  def "onComplete callback, 404 -> exception"() {
    given:
    def response = new Response()
    response.withStatus(HttpStatus.NOT_FOUND)

    when:
    request.completed(response)

    then:
    def e = thrown(UnauthorizedException)
    e.message == "Error updating Mdx user id"
  }

  def "onComplete callback, 500 -> exception"() {
    given:
    def response = new Response()
    response.withStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    when:
    request.completed(response)

    then:
    def e = thrown(UnauthorizedException)
    e.message == "Error updating Mdx user id"
  }

  def "withProcessor"() {
    given:
    def dob = "1992-06-10"
    def email = "username@example.com"
    def fingerprint = "U-3aec5eee-80db-43be-847c-45f9f1fff12a"
    def firstName = "John"
    def id = "61527a1699c8e521a000bf47"
    def lastName = "Doe"
    def phone = "8015551155"
    def zip = "84045"

    when:
    def result = (MdxUserWrapper) request.process(new Response(request)
        .withStatus(HttpStatus.OK)
        .withBody("{\"user\":{\"id\":\"${id}\"," +
        "\"birthdate\":\"${dob}\"," +
        "\"gender\":\"MALE\"," +
        "\"logged_in_at\":1632865478," +
        "\"credit_score\":760," +
        "\"email\":\"${email}\"," +
        "\"first_name\":\"${firstName}\"," +
        "\"guid\":\"USR-1b92012c-b788-494b-84d7-cc46965a169b\"," +
        "\"is_disabled\":false," +
        "\"last_name\":\"${lastName}\"," +
        "\"metadata\":\"{\\\"fingerprint\\\":\\\"${fingerprint}\\\"}\"," +
        "\"phone\":\"${phone}\"," +
        "\"zip_code\":\"${zip}\"}}"
        ))

    then:
    def user = result.getUser()
    user.birthdate == dob
    user.email == email
    user.firstName == firstName
    user.id == id
    user.lastName == lastName
    user.getMetadataField("fingerprint", String) == fingerprint
    user.phone == phone
    user.zipCode == zip
  }
}
