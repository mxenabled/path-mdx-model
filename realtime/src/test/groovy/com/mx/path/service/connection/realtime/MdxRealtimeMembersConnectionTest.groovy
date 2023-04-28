package com.mx.path.service.connection.realtime

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

import com.mx.path.core.common.accessor.UnauthorizedException
import com.mx.path.core.common.accessor.UpstreamSystemUnavailable
import com.mx.path.core.common.http.HttpStatus
import com.mx.path.service.connection.realtime.MdxRealtimeConnection
import com.mx.path.service.connection.realtime.MdxRealtimeConnectionConfiguration
import com.mx.path.service.connection.realtime.MdxRealtimeMembersConnection
import com.mx.path.service.connection.realtime.model.MdxMember
import com.mx.path.testing.WithRequestExpectations

import spock.lang.Specification

class MdxRealtimeMembersConnectionTest extends Specification implements WithRequestExpectations {
  MdxRealtimeMembersConnection subject
  MdxRealtimeConnection connection

  def setup() {
    def mockConfig = mock(MdxRealtimeConnectionConfiguration)
    connection = setupConnection(new MdxRealtimeConnection(mockConfig)) as MdxRealtimeConnection
    subject = new MdxRealtimeMembersConnection(connection)
    when(mockConfig.getClientId()).thenReturn("clientId")
  }

  def "get"() {
    given:
    expectConnection(withPath("/clientId/users/userId/members/memberId.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"member\":{\"id\":\"x\"}}")
        }

    when:
    def mdxRealtimeResponse = subject.get("userId", "memberId")

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "get error"() {
    given:
    expectConnection(withPath("/clientId/users/userId/members/memberId.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.UNAUTHORIZED)
        }

    when:
    subject.get("userId", "memberId")

    then:
    def ex = thrown(UnauthorizedException)
    ex.message == "Encountered an authorization error getting MDX member"
  }

  def "create-1"() {
    given:
    expectConnection(withPath("/clientId/users/userId/members.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"member\":{\"id\":\"x\"}}")
        }

    def mdxMember = new MdxMember()

    when:
    def mdxRealtimeResponse = subject.create("userId", mdxMember)

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "create-2"() {
    given:
    expectConnection(withPath("/clientId/users/userId/members.json")
        .withMatcher { request ->
          return request.body == "{\n" +
              "  \"member\": {\n" +
              "    \"id\": \"memberId\",\n" +
              "    \"userkey\": \"userKey\"\n" +
              "  }\n" +
              "}"
        }
        )
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"member\":{\"id\":\"x\"}}")
        }

    when:
    def mdxRealtimeResponse = subject.create("userId", "memberId", "userKey")

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "create-3"() {
    given:
    expectConnection(withPath("/clientId/users/userId/members.json")
        .withMatcher { request ->
          return request.body == "{\n" +
              "  \"member\": {\n" +
              "    \"id\": \"memberId\",\n" +
              "    \"login\": \"login\",\n" +
              "    \"password\": \"abc\"\n" +
              "  }\n" +
              "}"
        }
        )
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"member\":{\"id\":\"x\"}}")
        }

    def charArray = ['a', 'b', 'c'] as char[]

    when:
    def mdxRealtimeResponse = subject.create("userId", "memberId", "login", charArray)

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "create error"() {
    given:
    expectConnection(withPath("/clientId/users/userId/members.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.UNAUTHORIZED)
        }

    def mdxMember = new MdxMember()

    when:
    def status = subject.create("userId", mdxMember)

    then:
    def ex = thrown(UnauthorizedException)
    ex.message == "Encountered an authorization error getting MDX member"
  }
}