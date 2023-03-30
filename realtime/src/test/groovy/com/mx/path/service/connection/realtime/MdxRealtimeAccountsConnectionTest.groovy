package com.mx.path.service.connection.realtime

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

import com.mx.common.accessors.UnauthorizedException
import com.mx.common.http.HttpStatus
import com.mx.common.http.MediaType
import com.mx.path.service.connection.realtime.MdxRealtimeAccountsConnection
import com.mx.path.service.connection.realtime.MdxRealtimeConnection
import com.mx.path.service.connection.realtime.MdxRealtimeConnectionConfiguration
import com.mx.path.service.connection.realtime.model.MdxAccount
import com.mx.path.testing.WithRequestExpectations

import spock.lang.Specification

class MdxRealtimeAccountsConnectionTest extends Specification implements WithRequestExpectations {
  MdxRealtimeAccountsConnection subject
  MdxRealtimeConnection connection

  def setup() {
    def mockConfig = mock(MdxRealtimeConnectionConfiguration)
    connection = setupConnection(new MdxRealtimeConnection(mockConfig)) as MdxRealtimeConnection
    subject = new MdxRealtimeAccountsConnection(connection)
    when(mockConfig.getClientId()).thenReturn("clientId")
  }

  def "create"() {
    given:
    expectConnection(withPath("/clientId/users/userId/members/memberId/accounts.json")
        .withMatcher { req ->
          return req.contentType == new MediaType("application", "vnd.moneydesktop.mdx.v5+json").toString()
        }
        )
        .toRespond { request, response ->
          response.withStatus(HttpStatus.OK)
          response.withBody("{\"account\":{\"id\":\"x\"}}")
        }

    def account = new MdxAccount().tap {
      setId("accountId")
    }

    when:
    def mdxRealtimeResponse = subject.create("userId", "memberId", account)

    then:
    noExceptionThrown()
    mdxRealtimeResponse.status == HttpStatus.OK
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object != null
    mdxRealtimeResponse.object.id == "x"
  }

  def "create error"() {
    given:
    expectConnection(withPath("/clientId/users/userId/members/memberId/accounts.json"))
        .toRespond { request, response ->
          response.withStatus(HttpStatus.UNAUTHORIZED)
        }

    def account = new MdxAccount().tap {
      setId("accountId")
    }

    when:
    subject.create("userId", "memberId", account)

    then:
    def ex = thrown(UnauthorizedException)
    ex.message == "Encountered an authorization error creating MDX account"
    ex.userMessage == "Encountered an authorization error creating MDX account"
  }
}