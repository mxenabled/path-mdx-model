package com.mx.path.model.mdx.accessor.profile;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.profile.SecurityQuestions;

/**
 * Accessor base for profile security questions
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/index.html#security-questions")
public class SecurityQuestionBaseAccessor extends Accessor {
  public SecurityQuestionBaseAccessor() {
  }

  @Deprecated
  public SecurityQuestionBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Lists security questions
   * @return
   */
  @GatewayAPI
  @API(description = "List security questions")
  public AccessorResponse<SecurityQuestions> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Updates security questions
   * @param securityQuestions
   * @return
   */
  @GatewayAPI
  @API(description = "update security questions")
  public AccessorResponse<SecurityQuestions> update(SecurityQuestions securityQuestions) {
    throw new AccessorMethodNotImplementedException();
  }
}
