package com.mx.path.model.mdx.web.controller

import static com.mx.path.model.mdx.model.id.v20240213.Authentication.DeviceRegistration.*
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doAnswer
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

import java.nio.charset.StandardCharsets

import javax.servlet.http.HttpServletRequest

import com.google.common.hash.Hashing
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mx.path.core.common.accessor.PathResponseStatus
import com.mx.path.core.common.collection.ObjectMap
import com.mx.path.core.context.RequestContext
import com.mx.path.core.context.ResponseContext
import com.mx.path.core.context.Session
import com.mx.path.core.context.store.SessionRepository
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.id.IdGateway
import com.mx.path.gateway.context.Scope
import com.mx.path.model.mdx.model.Resources
import com.mx.path.model.mdx.model.authorization.Authorization
import com.mx.path.model.mdx.model.authorization.HtmlPage
import com.mx.path.model.mdx.model.challenges.Action
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.challenges.DeepLinkData
import com.mx.path.model.mdx.model.challenges.Option
import com.mx.path.model.mdx.model.challenges.Question
import com.mx.path.model.mdx.model.id.Authentication
import com.mx.path.model.mdx.model.id.ForgotUsername
import com.mx.path.model.mdx.model.id.MfaChallenge
import com.mx.path.model.mdx.model.id.ResetPassword
import com.mx.path.model.mdx.model.id.UnlockUser
import com.mx.path.testing.WithMockery
import com.mx.path.testing.session.TestEncryptionService
import com.mx.path.testing.session.TestSessionRepository
import com.mx.testing.Utils

import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import spock.lang.Specification

class AuthenticationControllerTest extends Specification implements WithMockery {
  Authentication authentication
  com.mx.path.model.mdx.model.id.v20240213.Authentication authentication20240213
  AuthenticationController subject
  SessionRepository sessionRepository
  Gateway gateway
  IdGateway id
  Gson gson

  def setup() {
    subject = new AuthenticationController()

    id = spy(IdGateway.builder().build())
    gateway = spy(Gateway.builder().id(id).build())

    sessionRepository = Mock()
    Session.setRepositorySupplier({ -> sessionRepository })
    Utils.injectSessionEncryptionService()

    RequestContext.builder().build().register()
    ResponseContext.builder().build().register()

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

    authentication20240213 = new com.mx.path.model.mdx.model.id.v20240213.Authentication()
    authentication20240213.setLogin("login")
    authentication20240213.setPassword("p@\$\$w0rd".toCharArray())
    authentication20240213.setDeviceHeight(100)
    authentication20240213.setDeviceId("123456789")
    authentication20240213.setDeviceLatitude(13)
    authentication20240213.setDeviceLongitude(14)
    authentication20240213.setDeviceMake("Apple")
    authentication20240213.setDeviceModel("XS")
    authentication20240213.setDeviceOperatingSystem("iOS")
    authentication20240213.setDeviceOperatingSystemVersion("12")
    authentication20240213.setDeviceWidth(50)

    GsonBuilder builder = new GsonBuilder();
    Resources.registerResources(builder);

    gson = builder.create();
  }

  def cleanup() {
    BaseController.clearGateway()
    Session.clearSession()
    Session.setRepositorySupplier(null)
    RequestContext.clear()
    ResponseContext.clear()
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
    null == response.getBody().deviceRegistration
    RequestContext.current().feature == "identity"
  }

  def "loginWithPasswordSuccessful 20240213"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new com.mx.path.model.mdx.model.id.v20240213.Authentication().withUserId("user-1234")

    when:
    doReturn(new AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticate((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication))
    def response = subject.authenticate("client1234", buildRequest(authentication20240213, "application/vnd.mx.id.v6+json;version=20240213"))

    then:
    verify(id).authenticate((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication)) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    "user-1234" == response.getBody().userId
    RequestContext.current().feature == "identity"
    response.headers.get("Content-Type").contains("application/vnd.mx.mdx.v6+json;charset=UTF-8;version=20240213")
  }

  def "loginWithDeviceRegistrationSuccessful"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new Authentication().withUserId("user-1234")
    resultAuthentication.setDeviceRegistration(PRIMARY)

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticate(authentication)
    def response = subject.authenticate("client1234", authentication)

    then:
    verify(id).authenticate(authentication) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    "user-1234" == response.getBody().getUserId()
    PRIMARY == response.getBody().deviceRegistration
  }

  def "loginWithDeviceRegistrationSuccessful 20240213"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new com.mx.path.model.mdx.model.id.v20240213.Authentication().withUserId("user-1234")
    resultAuthentication.setDeviceRegistration(PRIMARY)

    when:
    doReturn(new AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).authenticate((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication))
    def response = subject.authenticate("client1234", buildRequest(authentication20240213, "application/vnd.mx.id.v6+json;version=20240213"))

    then:
    verify(id).authenticate((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication)) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    "user-1234" == response.getBody().userId
    RequestContext.current().feature == "identity"
    response.headers.get("Content-Type").contains("application/vnd.mx.mdx.v6+json;charset=UTF-8;version=20240213")
    PRIMARY == response.getBody().deviceRegistration
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

  def "dumpEncryptedLoginHashToSession() saves encrypted login as sha256 to session"() {
    given:
    // manually setup session
    SessionRepository repository = new TestSessionRepository()
    Session.setRepositorySupplier({ -> repository })
    TestEncryptionService testEncryptionService = new TestEncryptionService(new ObjectMap())
    Session.setEncryptionServiceSupplier({ -> testEncryptionService })
    Utils.injectSessionEncryptionService()

    def login = "user_1234"
    def authentication = new Authentication().tap { setLogin(login) }
    def subjectSpy = spy(subject)
    doReturn(new AccessorResponse<Authentication>().withResult(authentication)).when(subjectSpy).getAuthenticationResult(authentication)

    when:
    subjectSpy.authenticate("client1234", authentication)

    then:
    def hashedLogin = Session.current().get(Scope.Session, "loginHash")
    String sha256hex = Hashing.sha256()
        .hashString(login, StandardCharsets.UTF_8)
        .toString()
    hashedLogin == sha256hex
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

  def "loginWithForwardedResponseHeaders"() {
    given:
    AuthenticationController.setGateway(gateway)
    def challenge = new MfaChallenge()
    challenge.setId("NEW_PASSWORD")
    challenge.setQuestion("Yo, change your password!")
    challenge.setType("PASSWORD")
    def resultAuthentication = new Authentication()
    resultAuthentication.setUserId("U-1234")
    doAnswer(new Answer() {
          public Object answer(InvocationOnMock invocation) {
            ResponseContext.current().getHeaders().put("forwarded-h1", "v1")
            return new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK);
          }
        }).when(id).authenticate(authentication)

    when:
    def response = subject.authenticate("client1234", authentication)

    then:
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getHeaders().getFirst("forwarded-h1") == "v1"
    response.getBody().getUserId() == "U-1234"
  }

  def "respondToMfaSuccessful"() {
    given:
    AuthenticationController.setGateway(gateway)
    initActiveSession()
    def resultAuthentication = new Authentication().withUserId("user-1234")

    when:
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).resumeMFA((Authentication) any(Authentication))
    HttpServletRequest request = mock(HttpServletRequest.class)

    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(gson.toJson(authentication))))
    when(request.getHeader("mx-session-key")).thenReturn(Session.current().getId())
    when(request.getHeaders("Content-Type")).thenReturn(Collections.enumeration(["vnd.mx.mdx.v6+json"]))
    when(request.getHeaders("Accept")).thenReturn(Collections.enumeration(["vnd.mx.mdx.v6+json"]))
    ResponseEntity<Authentication> response = subject.resumeMfa("client-1234", Session.current().getId(), request)

    then:
    verify(id).resumeMFA((Authentication) any()) || true
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
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).resumeMFA((Authentication) any(Authentication))
    def response = subject.resumeMfa("client1234", Session.current().getId(), buildRequest(authentication, "vnd.mx.mdx.v6+json"))

    then:
    verify(id).resumeMFA((Authentication) any(Authentication)) || true
    HttpStatus.ACCEPTED == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == null
  }

  def buildRequest(Object body, String contentType) {
    HttpServletRequest request = mock(HttpServletRequest.class)
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(gson.toJson(body))))
    if (Session.current() != null) {
      when(request.getHeader("mx-session-key")).thenReturn(Session.current().getId())
    }
    when(request.getHeaders("Content-Type")).thenReturn(Collections.enumeration([contentType]))
    when(request.getHeaders("Accept")).thenReturn(Collections.enumeration([contentType]))

    return request
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
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).startAuthentication((Authentication) any(Authentication))
    def response = subject.start("client1234", buildRequest(authentication, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(id).startAuthentication((Authentication) any(Authentication)) || true
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
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).startAuthentication((Authentication) any(Authentication))
    def response = subject.start("client1234", buildRequest(authentication, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(id).startAuthentication((Authentication) any(Authentication)) || true
    HttpStatus.ACCEPTED == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == null
    Session.current().getSessionState() == Session.SessionState.UNAUTHENTICATED
  }

  def "/authentications/start without token - FederatedLogin On - 20240213"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new com.mx.path.model.mdx.model.id.v20240213.Authentication()
    resultAuthentication.setChallenges(new ArrayList<Challenge>().tap {
      add(new Challenge().tap {
        it.setActions(new ArrayList<Action>().tap {
          add(new Action().tap {
            it.setDeepLinkData(new DeepLinkData().tap {
              setDeepLink("https://someUrlForFederatedLogin.com/")
            })
          })
        })
      })
    })

    when:
    doReturn(new AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).startAuthentication((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication))
    def response = subject.start("client1234", buildRequest(authentication, "application/vnd.mx.mdx.v6+json;version=20240213"))

    then:
    verify(id).startAuthentication((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication)) || true
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
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).startAuthentication((Authentication) any(Authentication))
    def response = subject.start("client1234", buildRequest(authentication, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(id).startAuthentication((Authentication) any(Authentication)) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.body.getUserId() == "U-111"
    response.body.refreshToken == "someRefreshToken"
    Session.current().getSessionState() == Session.SessionState.AUTHENTICATED
  }

  def "/authentications/start with refreshToken - FederatedLogin On 20240213"() {
    given:
    AuthenticationController.setGateway(gateway)
    def resultAuthentication = new com.mx.path.model.mdx.model.id.v20240213.Authentication().tap {
      setRefreshToken("someRefreshToken")
      setUserId("U-111")
    }

    when:
    doReturn(new AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).startAuthentication((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication))
    def response = subject.start("client1234", buildRequest(authentication, "application/vnd.mx.mdx.v6+json;version=20240213"))

    then:
    verify(id).startAuthentication((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication)) || true
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
    doReturn(new AccessorResponse<Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).resumeMFA((Authentication) any(Authentication))
    def response = subject.resumeMfa("client1234", Session.current().getId(), buildRequest(authentication, "application/vnd.mx.mdx.v6+json"))

    then:
    verify(id).resumeMFA((Authentication) any(Authentication)) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == "user-1234"
    Session.current().getSessionState() == Session.SessionState.AUTHENTICATED
  }

  def "/authentications/id - With Token 20240213"() {
    given:
    AuthenticationController.setGateway(gateway)
    initActiveSession()
    authentication20240213.setToken("Abcd1234")
    def resultAuthentication = new com.mx.path.model.mdx.model.id.v20240213.Authentication().withUserId("user-1234")

    when:
    doReturn(new AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication>().withResult(resultAuthentication).withStatus(PathResponseStatus.OK)).when(id).resumeMFA((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication))
    def response = subject.resumeMfa("client1234", Session.current().getId(), buildRequest(authentication20240213, "application/vnd.mx.mdx.v6+json; version=20240213"))

    then:
    verify(id).resumeMFA((com.mx.path.model.mdx.model.id.v20240213.Authentication) any(com.mx.path.model.mdx.model.id.v20240213.Authentication)) || true
    HttpStatus.OK == response.getStatusCode()
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.getBody().getUserId() == "user-1234"
    Session.current().getSessionState() == Session.SessionState.AUTHENTICATED
    response.headers.get("Content-Type").contains("application/vnd.mx.mdx.v6+json;charset=UTF-8;version=20240213")
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

  def "unlockUser"() {
    given:
    AuthenticationController.setGateway(gateway)
    def unlockUser = new UnlockUser()
    doReturn(new AccessorResponse<UnlockUser>().withResult(unlockUser).withStatus(PathResponseStatus.OK)).when(id).unlockUser(unlockUser)

    when:
    def response = subject.unlockUser(unlockUser)

    then:
    verify(gateway).id()              || true
    verify(id).unlockUser(unlockUser) || true
    Session.current().getId() == response.getHeaders().getFirst("mx-session-key")
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "unlockUser - ACCEPTED"() {
    given:
    AuthenticationController.setGateway(gateway)
    def unlockUser = new UnlockUser()
    def challenge = new Challenge().tap {
      id = "INITIAL_USER_UNLOCK"
      questions = new ArrayList<Question>().tap {
        add(new Question().tap {
          id = "userName"
          prompt = "username"
          promptType = "text"
        })
        add(new Question().tap {
          id = "mfaChoice"
          prompt = "Additional verification needed. Please select a delivery method to receive the verification code"
          options = new ArrayList<Option>().tap {
            add(new Option().tap {
              id = "sms"
              name = "SMS Text"
            })
            add(new Option().tap {
              id = "call"
              name = "Voice Call"
            })
            add(new Option().tap {
              id = "email"
              name = "Email"
            })
          }
        })
      }
    }
    def accessorResponse = new UnlockUser().tap { challenges = [challenge] }
    doReturn(new AccessorResponse<UnlockUser>().withResult(accessorResponse).withStatus(PathResponseStatus.ACCEPTED)).when(id).unlockUser(unlockUser)

    when:
    def response = subject.answerUnlockUser(unlockUser)

    then:
    verify(gateway).id()              || true
    verify(id).unlockUser(unlockUser) || true
    response.statusCode == HttpStatus.ACCEPTED
    response.getBody().challenges.size() > 0
  }

  def "unlockUser - NO_CONTENT"() {
    given:
    AuthenticationController.setGateway(gateway)
    def unlockUser = new UnlockUser().tap { challenges = [challenge] }
    doReturn(new AccessorResponse<UnlockUser>().withResult(new UnlockUser()).withStatus(PathResponseStatus.NO_CONTENT)).when(id).unlockUser(unlockUser)

    when:
    def response = subject.answerUnlockUser(unlockUser)

    then:
    verify(gateway).id()              || true
    verify(id).unlockUser(unlockUser) || true
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
