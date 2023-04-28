package com.mx.path.service.connection.realtime

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

import com.mx.path.connect.messaging.remote.models.RemoteUser
import com.mx.path.core.common.accessor.ResourceNotFoundException
import com.mx.path.core.common.accessor.UnauthorizedException
import com.mx.path.core.common.http.HttpStatus
import com.mx.path.service.connection.realtime.model.MdxUser
import com.mx.path.testing.WithRequestExpectations

import spock.lang.Specification

class MdxRealtimeUsersConnectionTest extends Specification implements WithRequestExpectations {
  MdxRealtimeUsersConnection subject
  MdxRealtimeConnection connection

  def setup() {
    def mockConfig = mock(MdxRealtimeConnectionConfiguration)
    connection = setupConnection(new MdxRealtimeConnection(mockConfig)) as MdxRealtimeConnection
    subject = new MdxRealtimeUsersConnection(connection)
    when(mockConfig.getClientId()).thenReturn("clientId")
  }

  def "get"() {
    given:
    expectConnection(withPath("/clientId/users/userId.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"user\":{\"id\":\"x\"}}")
        }

    when:
    def mdxRealtimeResponse = subject.get("userId")

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "get error"() {
    given:
    expectConnection(withPath("/clientId/users/userId.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.UNAUTHORIZED)
        }

    when:
    subject.get("userId")

    then:
    def ex = thrown(UnauthorizedException)
    ex.message == "Encountered an authorization error getting MDX user"
  }

  def "create-1"() {
    given:
    expectConnection(withPath("/clientId/users.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"user\":{\"id\":\"x\"}}")
        }

    when:
    def mdxRealtimeResponse = subject.create("userId")

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "create-2"() {
    given:
    expectConnection(withPath("/clientId/users.json")
        .withMatcher {req ->
          return req.body == "{\n" +
              "  \"user\": {\n" +
              "    \"id\": \"userId\",\n" +
              "    \"email\": \"email\",\n" +
              "    \"first_name\": \"firstName\",\n" +
              "    \"last_name\": \"lastName\",\n" +
              "    \"phone\": \"phone\"\n" +
              "  }\n" +
              "}"
        })
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"user\":{\"id\":\"x\"}}")
        }

    def user = new RemoteUser().tap {
      firstName = "firstName"
      email = "email"
      lastName = "lastName"
      phone = "phone"
    }

    when:
    def mdxRealtimeResponse = subject.create("userId", user)

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "create error"() {
    given:
    expectConnection(withPath("/clientId/users.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.UNAUTHORIZED)
        }

    when:
    subject.create("userId")

    then:
    def ex = thrown(UnauthorizedException)
    ex.message == "Encountered an authorization error creating MDX user"
  }

  def "delete"() {
    given:
    expectConnection(withPath("/clientId/users/userId.xml"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.NO_CONTENT)
        }

    when:
    def status = subject.delete("userId")

    then:
    noExceptionThrown()
    status == HttpStatus.NO_CONTENT
  }

  def "delete error"() {
    given:
    expectConnection(withPath("/clientId/users/userId.xml"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.UNAUTHORIZED)
        }

    when:
    subject.delete("userId")

    then:
    thrown(RuntimeException)
  }

  def "update"() {
    given:
    expectConnection(withPath("/clientId/users/userId.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"user\":{\"id\":\"x\"}}")
        }

    when:
    def mdxRealtimeResponse = subject.update("userId", new MdxUser())

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "update error"() {
    given:
    expectConnection(withPath("/clientId/users/userId.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.UNAUTHORIZED)
        }

    when:
    subject.update("userId", new MdxUser())

    then:
    def ex = thrown(UnauthorizedException)
    ex.message == "Encountered an authorization error updating MDX user"
  }

  def "re-map id"() {
    given:
    expectConnection(withPath("/clientId/users/userId/remap_id.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"user\":{\"id\":\"x\"}}")
        }

    when:
    def mdxRealtimeResponse = subject.remapId("userId", "userId2")

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "re-map id error"() {
    given:
    expectConnection(withPath("/clientId/users/userId/remap_id.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.UNAUTHORIZED)
        }

    when:
    subject.remapId("userId", "userId2")

    then:
    def ex = thrown(UnauthorizedException)
    ex.message == "Encountered an authorization error remapping MDX user"
  }
}