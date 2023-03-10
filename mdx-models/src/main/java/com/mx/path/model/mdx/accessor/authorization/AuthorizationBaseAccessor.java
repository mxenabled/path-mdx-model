package com.mx.path.model.mdx.accessor.authorization;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.authorization.Authorization;
import com.mx.path.model.mdx.model.authorization.HtmlPage;

/**
 * Accessor base for authorization operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/authorization/#mdx-authorization")
public abstract class AuthorizationBaseAccessor extends Accessor {

  public AuthorizationBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create authorization
   * @param authorization
   * @return
   */
  @GatewayAPI
  @API(description = "Create authorization")
  public AccessorResponse<Authorization> create(Authorization authorization) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * An HTML responder used to handle non-standard webview requirements.
   * @param token needed to continue SSO
   * @return HTML
   */
  @GatewayAPI
  @API(description = "An HTML responder used to handle non-standard webview requirements.")
  public AccessorResponse<HtmlPage> callback(String token) {
    throw new AccessorMethodNotImplementedException();
  }

}
