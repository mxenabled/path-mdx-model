package com.mx.path.model.mdx.accessor.profile;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.profile.ChallengeQuestions;

/**
 * Accessor base for profile challenge questions
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/index.html#challenge-questions")
public class ChallengeQuestionBaseAccessor extends Accessor {
  public ChallengeQuestionBaseAccessor() {
  }

  @Deprecated
  public ChallengeQuestionBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Lists challenge questions
   * @return
   */
  @GatewayAPI
  @API(description = "List challenge questions")
  public AccessorResponse<ChallengeQuestions> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Updates challenge questions
   * @param challengeQuestions
   * @return
   */
  @GatewayAPI
  @API(description = "update challenge questions")
  public AccessorResponse<ChallengeQuestions> update(ChallengeQuestions challengeQuestions) {
    throw new AccessorMethodNotImplementedException();
  }
}
