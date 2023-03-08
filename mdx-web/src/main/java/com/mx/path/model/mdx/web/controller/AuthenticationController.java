package com.mx.path.model.mdx.web.controller;

import java.util.Objects;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.accessors.BadRequestException;
import com.mx.common.lang.Strings;
import com.mx.models.authorization.HtmlPage;
import com.mx.models.id.Authentication;
import com.mx.models.id.ForgotUsername;
import com.mx.models.id.ResetPassword;
import com.mx.path.model.context.Session;
import com.mx.path.model.context.Session.SessionState;

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

  @RequestMapping(value = { "/authentications", "/sessions" }, method = RequestMethod.POST, consumes = { MDX_MEDIA, MDX_ONDEMAND_MEDIA }, produces = { MDX_MEDIA, MDX_ONDEMAND_MEDIA })
  public final ResponseEntity<Authentication> authenticate(@PathVariable("clientId") String clientId, @RequestBody Authentication requestAuthentication) {

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

  @RequestMapping(value = "/authentications/{sessionId}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<Authentication> resumeMfa(@PathVariable("clientId") String clientId, @PathVariable("sessionId") String sessionKey, @RequestBody Authentication requestAuthentication) {
    if (!Objects.equals(sessionKey, Session.current().getId())) {
      throw new BadRequestException("Session key mismatch. Header and path session keys don't match.", "Session key mismatch. Header and path session keys don't match.").withReport(true);
    }

    AccessorResponse<Authentication> response = gateway()
        .id()
        .resumeMFA(requestAuthentication);
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
  }

  @RequestMapping(value = "/authentications/start", method = RequestMethod.POST, consumes = MDX_MEDIA)
  public final ResponseEntity<Authentication> start(@PathVariable("clientId") String clientId, @RequestBody Authentication requestAuthentication) {

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

    AccessorResponse<Authentication> response = gateway().id().startAuthentication(requestAuthentication);
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
  }

  @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
  public final ResponseEntity<ResetPassword> resetPassword() {
    AccessorResponse<ResetPassword> response = gateway().id().resetPassword();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/reset_password/challenges/{challengeId}", method = RequestMethod.PUT, consumes = MDX_MEDIA)
  public final ResponseEntity<ResetPassword> resetPassword(@RequestBody ResetPassword resetPasswordAuthentication) {
    AccessorResponse<ResetPassword> response = gateway().id().answerResetPassword(resetPasswordAuthentication);
    ResetPassword result = response.getResult();
    // Return 202 returning challenge questions
    HttpStatus status = HttpStatus.NO_CONTENT;
    if (result.getChallenge() != null) {
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

  @SuppressWarnings("PMD.UselessParentheses")
  private AccessorResponse<Authentication> getAuthenticationResult(Authentication requestAuthentication) {
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

      throw new BadRequestException("Invalid authentication body", "Invalid authentication body");
    }
    return result;
  }
}
