package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify

import com.mx.path.core.context.Session
import com.mx.path.core.context.store.SessionRepository
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.origination.OriginationGateway
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.challenges.Question
import com.mx.path.model.mdx.model.origination.Origination

import org.springframework.http.HttpStatus

import spock.lang.Specification

class OriginationsControllerTest extends Specification {
  OriginationsController subject
  Gateway gateway
  OriginationGateway originationGateway
  SessionRepository sessionRepository

  def setup() {
    sessionRepository = Mock()
    Session.setRepositorySupplier({ -> sessionRepository })

    originationGateway = spy(OriginationGateway.builder().build())
    gateway = spy(Gateway.builder().originations(originationGateway).build())
    BaseController.setGateway(gateway)

    subject = new OriginationsController()
  }

  def cleanup() {
    BaseController.clearRepository()
    Session.clearSession()
    Session.setRepositorySupplier(null)
  }

  def "start interacts with gateway"() {
    given:
    def origination = new Origination().tap { setChallenges(Arrays.asList(new Challenge())) }
    doReturn(new AccessorResponse<Origination>().withResult(origination)).when(originationGateway).start()

    when:
    def response = subject.start()

    then:
    verify(originationGateway).start() || true
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "answerChallenge interacts with gateway - HttpStatus.OK"() {
    given:
    initActiveSession()
    def challenge = new Challenge().tap {
      setQuestions(Arrays.asList(new Question().tap {
        setId("token")
        setAnswer("1234")
      }))
    }
    def origination = new Origination().tap { setUserId("a user id") }
    doReturn(new AccessorResponse<Origination>().withResult(origination)).when(originationGateway).answerChallenge(any(), any(), any())

    when: "accessor response with no challenges"
    def response = subject.answerChallenge("OR-1", "1", new Challenge())

    then:
    verify(originationGateway).answerChallenge(any(), any(), any()) || true
    response.statusCode == HttpStatus.OK

    when: "accessor response with 'token' challenge question"
    origination.setChallenges(Arrays.asList(challenge))
    response = subject.answerChallenge("OR-1", "1", new Challenge())

    then:
    verify(originationGateway, times(2)).answerChallenge(any(), any(), any()) || true
    response.statusCode == HttpStatus.OK
  }

  def "answerChallenge interacts with gateway - HttpStatus.ACCEPTED"() {
    given:
    initActiveSession()
    def challenge = new Challenge().tap {
      setQuestions(Arrays.asList(new Question().tap {
        setId("question")
        setAnswer("answer")
      }))
    }
    def origination = new Origination().tap {
      setUserId("a user id")
      setChallenges(Arrays.asList(challenge))
    }
    doReturn(new AccessorResponse<Origination>().withResult(origination)).when(originationGateway).answerChallenge(any(), any(), any())

    when:
    def response = subject.answerChallenge("OR-1", "1", new Challenge())

    then:
    verify(originationGateway).answerChallenge(any(), any(), any()) || true
    response.statusCode == HttpStatus.ACCEPTED
  }

  private Session initActiveSession() {
    Session session = new Session()
    session.setId("session-1234")
    sessionRepository.load("session-1234") >> session
    Session.loadSession("session-1234")

    return session
  }

  def "authenticated user start interacts with gateway"() {
    given:
    initActiveSession()
    def origination = new Origination().tap { setChallenges(Arrays.asList(new Challenge())) }
    doReturn(new AccessorResponse<Origination>().withResult(origination)).when(originationGateway).authenticatedUserStart(any())

    when:
    def response = subject.authenticatedUserStart("U-1234")

    then:
    verify(originationGateway).authenticatedUserStart(any()) || true
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "authenticated user answerChallenge interacts with gateway - HttpStatus.OK"() {
    given:
    initActiveSession()
    def challenge = new Challenge().tap {
      setQuestions(Arrays.asList(new Question().tap {
        setId("token")
        setAnswer("1234")
      }))
    }
    def origination = new Origination().tap { setUserId("a user id") }
    doReturn(new AccessorResponse<Origination>().withResult(origination)).when(originationGateway).authenticatedUserAnswerChallenge(any(), any(), any(), any())

    when: "accessor response with no challenges"
    def response = subject.authenticatedUserAnswerChallenge("U-1234", "OR-1", "1", new Challenge())

    then:
    verify(originationGateway).authenticatedUserAnswerChallenge(any(), any(), any(), any()) || true
    response.statusCode == HttpStatus.OK

    when: "accessor response with 'token' challenge question"
    origination.setChallenges(Arrays.asList(challenge))
    response = subject.authenticatedUserAnswerChallenge("U-1234", "OR-1", "1", new Challenge())

    then:
    verify(originationGateway, times(2)).authenticatedUserAnswerChallenge(any(), any(), any(), any()) || true
    response.statusCode == HttpStatus.OK
  }

  def "authenticated user answerChallenge interacts with gateway - HttpStatus.ACCEPTED"() {
    given:
    initActiveSession()
    def challenge = new Challenge().tap {
      setQuestions(Arrays.asList(new Question().tap {
        setId("question")
        setAnswer("answer")
      }))
    }
    def origination = new Origination().tap {
      setUserId("a user id")
      setChallenges(Arrays.asList(challenge))
    }
    doReturn(new AccessorResponse<Origination>().withResult(origination)).when(originationGateway).authenticatedUserAnswerChallenge(any(), any(), any(), any())

    when:
    def response = subject.authenticatedUserAnswerChallenge("U-1234","OR-1", "1", new Challenge())

    then:
    verify(originationGateway).authenticatedUserAnswerChallenge(any(), any(), any(), any()) || true
    response.statusCode == HttpStatus.ACCEPTED
  }
}
