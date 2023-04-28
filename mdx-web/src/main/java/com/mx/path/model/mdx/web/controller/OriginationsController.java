package com.mx.path.model.mdx.web.controller;

import com.mx.path.core.common.accessor.BadRequestException;
import com.mx.path.core.context.Session;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.challenges.Challenge;
import com.mx.path.model.mdx.model.origination.Origination;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class OriginationsController extends BaseController {

  public OriginationsController() {
  }

  @RequestMapping(value = "/originations/start", method = RequestMethod.POST)
  public final ResponseEntity<Origination> start() {
    // Delete existing session if it exists;
    Session.deleteCurrent();
    Session.createSession();

    AccessorResponse<Origination> response = gateway().originations().start();
    Origination result = response.getResult();
    result.setId(getCurrentSessionId());
    HttpHeaders headers = new HttpHeaders();
    headers.add("mx-session-key", getCurrentSessionId());

    // Return 202 with challenges
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders(), headers), HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/originations/{id}/challenges/{challengeId}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Origination> answerChallenge(
      @PathVariable("id") String id,
      @PathVariable("challengeId") String challengeId, @RequestBody Challenge challenge) {
    AccessorResponse<Origination> response = gateway().originations().answerChallenge(id, challengeId, challenge);
    Origination result = response.getResult();

    result.setId(getCurrentSessionId());
    HttpHeaders headers = new HttpHeaders();
    headers.add("mx-session-key", getCurrentSessionId());

    // Return 200 when series of challenges ends origination
    if (result.getChallenges() == null || result.getChallenges().isEmpty()
        || result.getChallenges().get(0).getQuestions().size() > 0
            && result.getChallenges().get(0).getQuestions().get(0).getId().equalsIgnoreCase("token")) {
      return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders(), headers), HttpStatus.OK);
    }

    // Return 202 with challenges
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders(), headers), HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/users/{userId}/originations/authenticated_start", method = RequestMethod.POST)
  public final ResponseEntity<Origination> authenticatedUserStart(
      @PathVariable("userId") String userId) {
    AccessorResponse<Origination> response = gateway().originations().authenticatedUserStart(userId);
    Origination result = response.getResult();
    result.setId(getCurrentSessionId());

    // Return 202 with challenges
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/users/{userId}/originations/{id}/authenticated_challenges/{challengeId}", method = RequestMethod.PUT, consumes = BaseController.MDX_MEDIA)
  public final ResponseEntity<Origination> authenticatedUserAnswerChallenge(
      @PathVariable("userId") String userId,
      @PathVariable("id") String id,
      @PathVariable("challengeId") String challengeId, @RequestBody Challenge challenge) {
    AccessorResponse<Origination> response = gateway().originations().authenticatedUserAnswerChallenge(userId, id, challengeId, challenge);
    Origination result = response.getResult();

    result.setId(getCurrentSessionId());

    // Return 200 when series of challenges ends origination
    if (result.getChallenges() == null || result.getChallenges().isEmpty()
        || result.getChallenges().get(0).getQuestions().size() > 0
            && result.getChallenges().get(0).getQuestions().get(0).getId().equalsIgnoreCase("token")) {
      return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
    }

    // Return 202 with challenges
    return new ResponseEntity<>(result.wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.ACCEPTED);
  }

  private static String getCurrentSessionId() {
    if (Session.current() == null) {
      throw new BadRequestException("Current session is null. No session id could be found.", "Current session is null. No session id could be found.").withReport(true);
    }

    return Session.current().getId();
  }
}
