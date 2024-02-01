package com.mx.path.model.mdx.web.controller;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.google.common.hash.Hashing;
import com.mx.path.core.common.accessor.BadRequestException;
import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.context.Session;
import com.mx.path.core.context.Session.SessionState;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.gateway.context.Scope;
import com.mx.path.model.mdx.model.authorization.HtmlPage;
import com.mx.path.model.mdx.model.id.Authentication;
import com.mx.path.model.mdx.model.id.ForgotUsername;
import com.mx.path.model.mdx.model.id.ResetPassword;
import com.mx.path.model.mdx.model.id.UnlockUser;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AuthenticationController extends BaseController {

  @SuppressWarnings("MagicNumber")
  @RequestMapping(value = "/authentications", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<?> authenticate(@PathVariable("clientId") String clientId, HttpServletRequest request) {
    return versioned(request)
        .defaultVersion(Authentication.class, Authentication.class, authentication -> {
          return authenticate(clientId, authentication);
        })
        .version(20240213, com.mx.path.model.mdx.model.id.v20240213.Authentication.class, com.mx.path.model.mdx.model.id.v20240213.Authentication.class, authentication -> {
          return authenticate(clientId, authentication);
        })
        .execute();
  }

  /**
   * Legacy and MDXOnDemand version of authenticate
   *
   * @param clientId
   * @param requestAuthentication
   * @return
   */
  @RequestMapping(value = { "/sessions" }, method = RequestMethod.POST, consumes = MDX_ONDEMAND_MEDIA, produces = MDX_ONDEMAND_MEDIA)
  public final ResponseEntity<Authentication> authenticate(@PathVariable("clientId") String clientId, @RequestBody Authentication requestAuthentication) {
    ensureFeature("identity");

    // Delete existing session if it exists;
    Session.deleteCurrent();
    Session.createSession();

    // Store clientId
    Session.current().setClientId(clientId);
    Session.current().setDeviceId(requestAuthentication.getDeviceId());
    Session.current().setDeviceMake(requestAuthentication.getDeviceMake());
    Session.current().setDeviceModel(requestAuthentication.getDeviceModel());
    Session.current().setDeviceOperatingSystem(requestAuthentication.getDeviceOperatingSystem());
    Session.current().setDeviceOperatingSystemVersion(requestAuthentication.getDeviceOperatingSystemVersion());
    Session.current().setDeviceHeight(requestAuthentication.getDeviceHeight());
    Session.current().setDeviceLatitude(requestAuthentication.getDeviceLatitude());
    Session.current().setDeviceLongitude(requestAuthentication.getDeviceLongitude());
    Session.current().setDeviceWidth(requestAuthentication.getDeviceWidth());

    // Store login for troubleshooting failed authentication
    dumpEncryptedLoginHashToSession(requestAuthentication.getLogin());

    AccessorResponse<Authentication> accessorResponse;
    accessorResponse = getAuthenticationResult(requestAuthentication);

    accessorResponse.getResult().withId(Session.current().getId());
    HttpHeaders headers = new HttpHeaders();
    headers.add("mx-session-key", Session.current().getId());

    // Session is authenticated if the user id is set.
    if (accessorResponse.getResult().getUserId() != null) {
      Session.current().setUserId(accessorResponse.getResult().getUserId());
      Session.current().setSessionState(SessionState.AUTHENTICATED);
    }

    HttpStatus status = HttpStatus.OK;
    if (accessorResponse.getResult().getChallenges() != null
        && accessorResponse.getResult().getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    }

    return new ResponseEntity<>(accessorResponse.getResult().wrapped(), createMultiMapForResponse(accessorResponse.getHeaders(), headers),
        status);
  }

  @RequestMapping(value = "/authentications/{sessionId}/callback", method = RequestMethod.GET, produces = "text/html")
  public final ResponseEntity<String> callback(@RequestParam("token") String token) {
    HtmlPage htmlPage = gateway().id().callback(token).getResult();
    return ResponseEntity.ok().body(htmlPage.getContent());
  }

  @RequestMapping(value = "/authentications/{sessionKey}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> logout() {
    try {
      String sessionKey = null;
      if (Session.current() != null) {
        sessionKey = Session.current().getId();
      }
      gateway().id().logout(sessionKey);
    } finally {
      Session.deleteCurrent();
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @SuppressWarnings("MagicNumber")
  @RequestMapping(value = "/authentications/{sessionId}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<?> resumeMfa(@PathVariable("clientId") String clientId, @PathVariable("sessionId") String sessionKey, HttpServletRequest request) {
    if (!Objects.equals(sessionKey, Session.current().getId())) {
      throw new BadRequestException("Session key mismatch. Header and path session keys don't match.", "Session key mismatch. Header and path session keys don't match.").withReport(true);
    }

    return versioned(request)
        .defaultVersion(Authentication.class, Authentication.class, authentication -> {
          AccessorResponse<Authentication> response = gateway()
              .id()
              .resumeMFA(authentication);
          response.getResult().withId(Session.current().getId());

          HttpHeaders headers = new HttpHeaders();
          headers.add("mx-session-key", response.getResult().getId());

          // Session is authenticated if the user id is set.
          if (response.getResult().getUserId() != null) {
            Session.current().setUserId(response.getResult().getUserId());
            Session.current().setSessionState(SessionState.AUTHENTICATED);
          }

          HttpStatus status = HttpStatus.OK;
          if (response.getResult().getChallenges() != null
              && response.getResult().getChallenges().size() > 0) {
            status = HttpStatus.ACCEPTED;
          }

          return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders(), headers), status);
        }).version(20240213, com.mx.path.model.mdx.model.id.v20240213.Authentication.class, com.mx.path.model.mdx.model.id.v20240213.Authentication.class, authentication -> {
          AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication> response = gateway()
              .id()
              .resumeMFA(authentication);
          response.getResult().withId(Session.current().getId());

          HttpHeaders headers = new HttpHeaders();
          headers.add("mx-session-key", response.getResult().getId());

          // Session is authenticated if the user id is set.
          if (response.getResult().getUserId() != null) {
            Session.current().setUserId(response.getResult().getUserId());
            Session.current().setSessionState(SessionState.AUTHENTICATED);
          }

          HttpStatus status = HttpStatus.OK;
          if (response.getResult().getChallenges() != null
              && response.getResult().getChallenges().size() > 0) {
            status = HttpStatus.ACCEPTED;
          }

          return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders(), headers), status);
        }).execute();
  }

  @SuppressWarnings("MagicNumber")
  @RequestMapping(value = "/authentications/start", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<?> start(@PathVariable("clientId") String clientId, HttpServletRequest request) {

    // Delete existing session if it exists;
    Session.deleteCurrent();
    Session.createSession();

    return versioned(request)
        .defaultVersion(Authentication.class, Authentication.class, authentication -> {
          // Store clientId
          Session.current().setClientId(clientId);
          Session.current().setDeviceId(authentication.getDeviceId());
          Session.current().setDeviceMake(authentication.getDeviceMake());
          Session.current().setDeviceModel(authentication.getDeviceModel());
          Session.current().setDeviceOperatingSystem(authentication.getDeviceOperatingSystem());
          Session.current().setDeviceOperatingSystemVersion(authentication.getDeviceOperatingSystemVersion());
          Session.current().setDeviceHeight(authentication.getDeviceHeight());
          Session.current().setDeviceLatitude(authentication.getDeviceLatitude());
          Session.current().setDeviceLongitude(authentication.getDeviceLongitude());
          Session.current().setDeviceWidth(authentication.getDeviceWidth());

          AccessorResponse<Authentication> response = gateway().id().startAuthentication(authentication);
          response.getResult().withId(Session.current().getId());
          HttpHeaders headers = new HttpHeaders();
          headers.add("mx-session-key", Session.current().getId());

          // Return 204 if challenges are null to indicate non-federated login
          // Return 202 to trigger follow-up PUT /authentications/{session_key} call w/ token when challenges are NOT null
          // Return 200 if user_id is NOT null, no follow-up PUT /authentications/{session_key} call needed
          HttpStatus status = HttpStatus.NO_CONTENT;
          if (response.getResult().getChallenges() != null
              && response.getResult().getChallenges().size() > 0) {
            status = HttpStatus.ACCEPTED;
          } else if (Strings.isNotBlank(response.getResult().getUserId())) {
            status = HttpStatus.OK;
            Session.current().setUserId(response.getResult().getUserId());
            Session.current().setSessionState(SessionState.AUTHENTICATED);
          }
          return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders(), headers), status);
        }).version(20240213, com.mx.path.model.mdx.model.id.v20240213.Authentication.class, com.mx.path.model.mdx.model.id.v20240213.Authentication.class, authentication -> {
          // Store clientId
          Session.current().setClientId(clientId);
          Session.current().setDeviceId(authentication.getDeviceId());
          Session.current().setDeviceMake(authentication.getDeviceMake());
          Session.current().setDeviceModel(authentication.getDeviceModel());
          Session.current().setDeviceOperatingSystem(authentication.getDeviceOperatingSystem());
          Session.current().setDeviceOperatingSystemVersion(authentication.getDeviceOperatingSystemVersion());
          Session.current().setDeviceHeight(authentication.getDeviceHeight());
          Session.current().setDeviceLatitude(authentication.getDeviceLatitude());
          Session.current().setDeviceLongitude(authentication.getDeviceLongitude());
          Session.current().setDeviceWidth(authentication.getDeviceWidth());

          AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication> response = gateway().id().startAuthentication(authentication);
          response.getResult().withId(Session.current().getId());
          HttpHeaders headers = new HttpHeaders();
          headers.add("mx-session-key", Session.current().getId());

          // Return 204 if challenges are null to indicate non-federated login
          // Return 202 to trigger follow-up PUT /authentications/{session_key} call w/ token when challenges are NOT null
          // Return 200 if user_id is NOT null, no follow-up PUT /authentications/{session_key} call needed
          HttpStatus status = HttpStatus.NO_CONTENT;
          if (response.getResult().getChallenges() != null
              && response.getResult().getChallenges().size() > 0) {
            status = HttpStatus.ACCEPTED;
          } else if (Strings.isNotBlank(response.getResult().getUserId())) {
            status = HttpStatus.OK;
            Session.current().setUserId(response.getResult().getUserId());
            Session.current().setSessionState(SessionState.AUTHENTICATED);
          }
          return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders(), headers), status);
        }).execute();
  }

  /***
   * Initiates the reset password workflow.
   *
   * Always creates a new session, discards old session
   *
   * @return
   */
  @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
  public final ResponseEntity<ResetPassword> resetPassword() {
    //This endpoint always creates a new session when it called even if there is an existing session being passed
    Session.deleteCurrent();
    Session.createSession();

    AccessorResponse<ResetPassword> response = gateway().id().resetPassword();

    HttpHeaders headers = new HttpHeaders();
    headers.add("mx-session-key", Session.current().getId());
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders(), headers), HttpStatus.OK);
  }

  @RequestMapping(value = "/reset_password/challenges/{challengeId}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<ResetPassword> resetPassword(@RequestBody ResetPassword resetPasswordAuthentication) {
    AccessorResponse<ResetPassword> response = gateway().id().answerResetPassword(resetPasswordAuthentication);
    ResetPassword result = response.getResult();
    // Return 202 returning challenge questions
    HttpStatus status = HttpStatus.NO_CONTENT;
    if (result.getChallenge() != null || result.getChallenges() != null
        && result.getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    }
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), status);
  }

  @RequestMapping(value = "/forgot_username", method = RequestMethod.POST)
  public final ResponseEntity<ForgotUsername> forgotUsername() {
    AccessorResponse<ForgotUsername> response = gateway().id().forgotUsername();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/forgot_username/challenges/{challengeId}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<ForgotUsername> forgotUsername(@RequestBody ForgotUsername forgotUsernameRequest) {
    AccessorResponse<ForgotUsername> response = gateway().id().answerForgotUsername(forgotUsernameRequest);
    ForgotUsername result = response.getResult();
    // Return 202 returning challenge questions
    HttpStatus status = HttpStatus.NO_CONTENT;
    if (result.getChallenge() != null) {
      status = HttpStatus.ACCEPTED;
    }
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), status);
  }

  @RequestMapping(value = "/unlock", method = RequestMethod.POST)
  public final ResponseEntity<UnlockUser> unlockUser(@RequestBody UnlockUser unlockUser) {
    //This endpoint always creates a new session when it called even if there is an existing session being passed
    Session.deleteCurrent();
    Session.createSession();

    AccessorResponse<UnlockUser> response = gateway().id().unlockUser(unlockUser);

    HttpHeaders headers = new HttpHeaders();
    headers.add("mx-session-key", Session.current().getId());
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders(), headers), HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/unlock", method = RequestMethod.PUT)
  public final ResponseEntity<UnlockUser> answerUnlockUser(@RequestBody UnlockUser unlockUser) {
    AccessorResponse<UnlockUser> response = gateway().id().unlockUser(unlockUser);
    UnlockUser result = response.getResult();
    // Return 202 returning challenge questions
    HttpStatus status = HttpStatus.NO_CONTENT;
    if (result.getChallenges() != null && result.getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    }
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), status);
  }

  // Package private

  final void dumpEncryptedLoginHashToSession(String login) {
    if (Strings.isNotBlank(login)) {
      String sha256hex = Hashing.sha256()
          .hashString(login, StandardCharsets.UTF_8)
          .toString();
      Session.current().sput(Scope.Session, "loginHash", sha256hex);
    }
  }

  @SuppressWarnings("PMD.UselessParentheses")
  final AccessorResponse<Authentication> getAuthenticationResult(Authentication requestAuthentication) {
    AccessorResponse<Authentication> result;
    if (Strings.isNotBlank(requestAuthentication.getToken())
        || Strings.isNotBlank(requestAuthentication.getAccessToken())
        || (Strings.isNotBlank(requestAuthentication.getLogin())
            && requestAuthentication.getPassword() != null
            && requestAuthentication.getPassword().length > 0)) {
      // ---------------------------------------------
      // Login/Password OR token Authentication
      result = gateway().id().authenticate(requestAuthentication);
    } else if (Strings.isNotBlank(requestAuthentication.getUserkey())) {
      // ---------------------------------------------
      // OnDemand Authentication
      result = gateway().id().authenticateWithUserKey(requestAuthentication);

    } else {
      // ---------------------------------------------
      // Invalid authentication request

      throw new BadRequestException("Invalid authentication body");
    }
    return result;
  }

  @SuppressWarnings("PMD.UselessParentheses")
  final AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication> getAuthenticationResult(com.mx.path.model.mdx.model.id.v20240213.Authentication requestAuthentication) {
    AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication> result;
    if (Strings.isNotBlank(requestAuthentication.getToken())
        || Strings.isNotBlank(requestAuthentication.getAccessToken())
        || (Strings.isNotBlank(requestAuthentication.getLogin())
            && requestAuthentication.getPassword() != null
            && requestAuthentication.getPassword().length > 0)) {
      // ---------------------------------------------
      // Login/Password OR token Authentication
      result = gateway().id().authenticate(requestAuthentication);
    } else {
      // ---------------------------------------------
      // Invalid authentication request

      throw new BadRequestException("Invalid authentication body");
    }

    return result;
  }

  /**
   * 20240213 version of authenticate
   *
   * @param clientId
   * @param requestAuthentication
   * @return
   */
  protected final ResponseEntity<com.mx.path.model.mdx.model.id.v20240213.Authentication> authenticate(@PathVariable("clientId") String clientId, @RequestBody com.mx.path.model.mdx.model.id.v20240213.Authentication requestAuthentication) {
    ensureFeature("identity");

    // Delete existing session if it exists;
    Session.deleteCurrent();
    Session.createSession();

    // Store clientId
    Session.current().setClientId(clientId);
    Session.current().setDeviceId(requestAuthentication.getDeviceId());
    Session.current().setDeviceMake(requestAuthentication.getDeviceMake());
    Session.current().setDeviceModel(requestAuthentication.getDeviceModel());
    Session.current().setDeviceOperatingSystem(requestAuthentication.getDeviceOperatingSystem());
    Session.current().setDeviceOperatingSystemVersion(requestAuthentication.getDeviceOperatingSystemVersion());
    Session.current().setDeviceHeight(requestAuthentication.getDeviceHeight());
    Session.current().setDeviceLatitude(requestAuthentication.getDeviceLatitude());
    Session.current().setDeviceLongitude(requestAuthentication.getDeviceLongitude());
    Session.current().setDeviceWidth(requestAuthentication.getDeviceWidth());

    // Store login for troubleshooting failed authentication
    dumpEncryptedLoginHashToSession(requestAuthentication.getLogin());

    AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication> accessorResponse;
    accessorResponse = getAuthenticationResult(requestAuthentication);

    accessorResponse.getResult().withId(Session.current().getId());
    HttpHeaders headers = new HttpHeaders();
    headers.add("mx-session-key", Session.current().getId());

    // Session is authenticated if the user id is set.
    if (accessorResponse.getResult().getUserId() != null) {
      Session.current().setUserId(accessorResponse.getResult().getUserId());
      Session.current().setSessionState(SessionState.AUTHENTICATED);
    }

    HttpStatus status = HttpStatus.OK;
    if (accessorResponse.getResult().getChallenges() != null
        && accessorResponse.getResult().getChallenges().size() > 0) {
      status = HttpStatus.ACCEPTED;
    }

    return new ResponseEntity<>(accessorResponse.getResult().wrapped(), createMultiMapForResponse(accessorResponse.getHeaders(), headers),
        status);
  }
}
