package com.mx.path.model.mdx.accessor.origination;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.challenges.Challenge;
import com.mx.path.model.mdx.model.origination.Origination;

/**
 * Accessor for origination operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/origination/#mdx-orignation">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/origination/#mdx-origination")
public abstract class OriginationBaseAccessor extends Accessor {

  public OriginationBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Start Origination
   * @return
   */
  @GatewayAPI
  @API(description = "start Origination process will return challenge as per client config")
  public AccessorResponse<Origination> start() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * answer a challenge
   * @param id
   * @param challenge
   * @return
   */
  @GatewayAPI
  @API(description = "answer an Origination challenge")
  public AccessorResponse<Origination> answerChallenge(String id, String challengeId, Challenge challenge) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Authenticated user origination start
   * @return
   */
  @GatewayAPI
  @API(description = "start Origination process will return challenge as per client config for an authenticated user")
  public AccessorResponse<Origination> authenticatedUserStart(String userId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * answer a challenge for an authenticated user
   * @param id
   * @param challenge
   * @return
   */
  @GatewayAPI
  @API(description = "answer an Origination challenge for an authenticated user")
  public AccessorResponse<Origination> authenticatedUserAnswerChallenge(String userId, String id, String challengeId, Challenge challenge) {
    throw new AccessorMethodNotImplementedException();
  }

}
