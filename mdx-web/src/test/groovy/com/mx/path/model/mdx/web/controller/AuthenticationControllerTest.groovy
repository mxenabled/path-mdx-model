package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.accessors.PathResponseStatus
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.id.IdGateway
import com.mx.path.model.context.RequestContext
import com.mx.path.model.context.Session
import com.mx.path.model.context.store.SessionRepository
import com.mx.path.model.mdx.model.authorization.Authorization
import com.mx.path.model.mdx.model.authorization.HtmlPage
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.challenges.Question
import com.mx.path.model.mdx.model.id.Authentication
import com.mx.path.model.mdx.model.id.ForgotUsername
import com.mx.path.model.mdx.model.id.MfaChallenge
import com.mx.path.model.mdx.model.id.ResetPassword
import com.mx.path.testing.WithMockery

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import spock.lang.Specification

class AuthenticationControllerTest extends Specification implements WithMockery {
  Authentication authentication
  AuthenticationController subject
  SessionRepository sessionRepository
  Gateway gateway
  IdGateway id

  def setup() {
    subject = new AuthenticationController()

    id = spy(IdGateway.builder().build())
    gateway = spy(Gateway.builder().id(id).build())

    sessionRepository = Mock()
    Session.setRepositorySupplier({ -> sessionRepository })
    RequestContext.builder().build().register()

    authentication = new Authentication()
    authentication.setLogin("login")
    authentication.setPassword("p@\$\$w0rd".toCharArray())
    authentication.setDeviceHeight(100)
    authentication.setDeviceId("123456789")
    authentication.setDeviceLatitude(13)
    authentication.setDeviceLongitude(14)
    authentication.setDeviceMake("Apple")
    authentication.setDeviceModel("XS")
    authentication.setDeviceOperatingSystem("iOS")
    authentication.setDeviceOperatingSystemVersion("12")
    authentication.setDeviceWidth(50)
  }

  def cleanup() {
    BaseController.clearRepository()
    Session.clearSession()
    Session.setRepositorySupplier(null)
    RequestContext.clear()
  }


  def "loginWithPasswordSuccessful"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication().withUserId("user-1234")

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticate(authentication)
    def response = subject.authenticate("client1234", authentication)

    then:
    verify(id).authenticate(authentication) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    "user-1234" == response.getBody().getUserId()
  }

  def "loginWithTokenSuccessful"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication().withUserId("user-1234")

    def tokenAuthetication = new Authentication()
    tokenAuthetication = new Authentication()
    tokenAuthetication.setToken("token")
    tokenAuthetication.setDeviceHeight(100)
    tokenAuthetication.setDeviceId("123456789")
    tokenAuthetication.setDeviceLatitude(13)
    tokenAuthetication.setDeviceLongitude(14)
    tokenAuthetication.setDeviceMake("Apple")
    tokenAuthetication.setDeviceModel("XS")
    tokenAuthetication.setDeviceOperatingSystem("iOS")
    tokenAuthetication.setDeviceOperatingSystemVersion("12")
    tokenAuthetication.setDeviceWidth(50)

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticate(tokenAuthetication)
    def response = subject.authenticate("client1234", tokenAuthetication)

    then:
    verify(id).authenticate(tokenAuthetication) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    "user-1234" == response.getBody().getUserId()
  }

  def "loginWithUserkeySuccessful"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication().withUserId("user-1234")
    authentication.setPassword(null)
    authentication.setLogin(null)
    authentication.setUserkey("USER-1234")

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticateWithUserKey(authentication)
    def response = subject.authenticate("client1234", authentication)

    then:
    verify(id).authenticateWithUserKey(authentication) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    "user-1234" == response.getBody().getUserId()
  }

  def "loginSavesDeviceIpToSessionAndRequestContext"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication().withUserId("user-1234")

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticate(authentication)
    subject.authenticate("client1234", authentication)

    then:
    verify(id).authenticate(authentication) || true
    authentication.getDeviceHeight() == Session.current().getDeviceHeight()
    authentication.getDeviceId() == Session.current().getDeviceId()
    authentication.getDeviceLatitude() == Session.current().getDeviceLatitude()
    authentication.getDeviceLongitude() == Session.current().getDeviceLongitude()
    authentication.getDeviceMake() == Session.current().getDeviceMake()
    authentication.getDeviceModel() == Session.current().getDeviceModel()
    authentication.getDeviceOperatingSystem() == Session.current().getDeviceOperatingSystem()
    authentication.getDeviceOperatingSystemVersion() == Session.current().getDeviceOperatingSystemVersion()
    authentication.getDeviceWidth() == Session.current().getDeviceWidth()
  }

  def "loginSavesClientIdSession"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication().withUserId("user-1234")

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticate(authentication)
    subject.authenticate("client1234", authentication)

    then:
    verify(id).authenticate(authentication) || true
    "client1234" == Session.current().getClientId()
  }

  def "loginWithPasswordWithChallenges"() {
    given:
    AuthenticationController.setGateway(gateway)
    def challenge = new MfaChallenge()
    challenge.setId("NEW_PASSWORD")
    challenge.setQuestion("Yo, change your password!")
    challenge.setType("PASSWORD")
    def resultAuthentication = new Authentication()
    def challenges = new ArrayList<MfaChallenge>()
    challenges.add(challenge)
    resultAuthentication.setChallenges(challenges)

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticate(authentication)
    def response = subject.authenticate("client1234", authentication)

    then:
    verify(id).authenticate(authentication) || true
    HttpStatus.ACCEPTED == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == null
  }

  def "respondToMfaSuccessful"() {
    given:
    AuthenticationController.setGateway(gateway)
    initActiveSession()
    def resultAuthentication = new Authentication().withUserId("user-1234")

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).resumeMFA(authentication)
    ResponseEntity<Authentication> response = subject.resumeMfa("client-1234", Session.current().getId(), authentication)

    then:
    verify(id).resumeMFA(authentication) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    "user-1234" == response.getBody().getUserId()
  }

  def "respondToMfaWithChallenges"() {
    given:
    AuthenticationController.setGateway(gateway)
    initActiveSession()
    def challenge = new MfaChallenge()
    challenge.setId("NEW_PASSWORD")
    challenge.setQuestion("Yo, change your password!")
    challenge.setType("PASSWORD")
    def resultAuthentication = new Authentication()
    def challenges = new ArrayList<MfaChallenge>()
    challenges.add(challenge)
    resultAuthentication.setChallenges(challenges)

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).resumeMFA(authentication)
    def response = subject.resumeMfa("client1234", Session.current().getId(), authentication)

    then:
    verify(id).resumeMFA(authentication) || true
    HttpStatus.ACCEPTED == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == null
  }

  def "callback"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultHtmlPage = new HtmlPage()

    when:
    doReturn(new AccessorResponse<HtmlPage>().withResult(resultHtmlPage).withStatus(PathResponseStatus.OK)).when(id).callback("token1234")
    def response = subject.callback("token1234")

    then:
    verify(id).callback("token1234") || true
    HttpStatus.OK == response.getStatusCode()
  }

  def "logoutDeletesSession"() {
    given:
    AuthenticationController.setGateway(gateway)
    def session = initActiveSession()

    when:
    doReturn(new AccessorResponse<Void>().withStatus(PathResponseStatus.OK)).when(id).logout(session.getId())
    def response = subject.logout()

    then:
    verify(id).logout(session.getId()) || true

    HttpStatus.NO_CONTENT == response.getStatusCode()
  }

  def "/authentications/start without token - FederatedLogin Off"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication()

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).startAuthentication(authentication)
    def response = subject.start("client1234", authentication)

    then:
    verify(id).startAuthentication(authentication) || true
    HttpStatus.NO_CONTENT == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == null
    Session.current().getSessionState() == Session.SessionState.UNAUTHENTICATED
  }

  def "/authentications/start without token - FederatedLogin On"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication()
    resultAuthentication.setChallenges(new ArrayList<MfaChallenge>().tap {
      add(new MfaChallenge().tap {
        setAuthorization(new Authorization().tap { setUrl("https://someUrlForFederatedLogin.com/") })
      })
    })

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).startAuthentication(authentication)
    def response = subject.start("client1234", authentication)

    then:
    verify(id).startAuthentication(authentication) || true
    HttpStatus.ACCEPTED == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == null
    Session.current().getSessionState() == Session.SessionState.UNAUTHENTICATED
  }

  def "/authentications/start with refreshToken - FederatedLogin On"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication().tap {
      setRefreshToken("someRefreshToken")
      setUserId("U-111")
    }

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).startAuthentication(authentication)
    def response = subject.start("client1234", authentication)

    then:
    verify(id).startAuthentication(authentication) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.body.getUserId() == "U-111"
    response.body.refreshToken == "someRefreshToken"
    Session.current().getSessionState() == Session.SessionState.AUTHENTICATED
  }

  def "/authentications/id - With Token"() {
    given:
    AuthenticationController.setGateway(gateway)
    initActiveSession()
    authentication.setToken("Abcd1234")
    def resultAuthentication = new Authentication().withUserId("user-1234")

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).resumeMFA(authentication)
    def response = subject.resumeMfa("client1234", Session.current().getId(), authentication)

    then:
    verify(id).resumeMFA(authentication) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == "user-1234"
    Session.current().getSessionState() == Session.SessionState.AUTHENTICATED
  }

  def "forgotUsername"() {
    given:
    AuthenticationController.setGateway(gateway)
    doReturn(new AccessorResponse<ForgotUsername>().withResult(new ForgotUsername()).withStatus(PathResponseStatus.OK)).when(id).forgotUsername()

    when:
    def response = subject.forgotUsername()

    then:
    verify(gateway).id()        || true
    verify(id).forgotUsername() || true
    response.statusCode == HttpStatus.OK
  }

  def "forgotUsernameResend - ACCEPTED"() {
    given:
    AuthenticationController.setGateway(gateway)
    def forgotUsername = new ForgotUsername().tap { setChallenge(getChallenge()) }
    def expected = new ForgotUsername().tap { setChallenge(new Challenge()) }
    doReturn(new AccessorResponse<ForgotUsername>().withResult(expected).withStatus(PathResponseStatus.ACCEPTED)).when(id).answerForgotUsername(forgotUsername)

    when:
    def response = subject.forgotUsername(forgotUsername)

    then:
    verify(gateway).id()                            || true
    verify(id).answerForgotUsername(forgotUsername) || true
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "answerForgotUsername - NO_CONTENT"() {
    given:
    AuthenticationController.setGateway(gateway)
    def forgotUsername = new ForgotUsername().tap { setChallenge(getChallenge()) }
    doReturn(new AccessorResponse<ForgotUsername>().withResult(new ForgotUsername()).withStatus(PathResponseStatus.NO_CONTENT)).when(id).answerForgotUsername(forgotUsername)

    when:
    def response = subject.forgotUsername(forgotUsername)

    then:
    verify(gateway).id()                            || true
    verify(id).answerForgotUsername(forgotUsername) || true
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "resetPassword"() {
    given:
    AuthenticationController.setGateway(gateway)
    doReturn(new AccessorResponse<ResetPassword>().withResult(new ResetPassword()).withStatus(PathResponseStatus.OK)).when(id).resetPassword()

    when:
    def response = subject.resetPassword()

    then:
    verify(gateway).id()       || true
    verify(id).resetPassword() || true
    response.statusCode == HttpStatus.OK
  }

  def "resetPassword - ACCEPTED"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resetPassword = new ResetPassword().tap { setChallenge(getChallenge()) }
    def expected = new ResetPassword().tap { setChallenge(new Challenge()) }
    doReturn(new AccessorResponse<ResetPassword>().withResult(expected).withStatus(PathResponseStatus.ACCEPTED)).when(id).answerResetPassword(resetPassword)

    when:
    def response = subject.resetPassword(resetPassword)

    then:
    verify(gateway).id()                          || true
    verify(id).answerResetPassword(resetPassword) || true
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "resetPassword - NO_CONTENT"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resetPassword = new ResetPassword().tap { setChallenge(getChallenge()) }
    doReturn(new AccessorResponse<ResetPassword>().withResult(new ResetPassword()).withStatus(PathResponseStatus.NO_CONTENT)).when(id).answerResetPassword(resetPassword)

    when:
    def response = subject.resetPassword(resetPassword)

    then:
    verify(gateway).id()                          || true
    verify(id).answerResetPassword(resetPassword) || true
    response.statusCode == HttpStatus.NO_CONTENT
  }

  // Private

  def getChallenge() {
    return new Challenge().tap {
      setId("CHALLENGE_ID_1")
      setQuestions(new ArrayList<Question>().tap {
        add(new Question().tap {
          setId("QUESTION_ID_1")
          setAnswer("06/09/1993")
        })
      })
    }
  }

  private Session initActiveSession() {
    Session session = new Session()
    session.setId("session-1234")
    sessionRepository.load("session-1234") >> session
    Session.loadSession("session-1234")

    return session
  }
}
