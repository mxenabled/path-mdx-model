package com.mx.accessors.id;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.models.authorization.HtmlPage;
import com.mx.models.id.Authentication;
import com.mx.models.id.ForgotUsername;
import com.mx.models.id.ResetPassword;

/**
 * Accessor for id operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/id/#mdx-id">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/id/#mdx-id")
public abstract class IdBaseAccessor extends Accessor {

  public IdBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Start Authenticate
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "start Authentication process by sending if login should be webview or normal login")
  public AccessorResponse<Authentication> startAuthentication(Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Authenticate session
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "Authenticate user")
  public AccessorResponse<Authentication> authenticate(Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Authenticate session
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "Authenticate user with user key")
  public AccessorResponse<Authentication> authenticateWithUserKey(Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * End current session
   */
  @GatewayAPI
  @API(description = "Terminate the session")
  public AccessorResponse<Void> logout(String sessionId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Resume MFA Session
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "Respond to MFA questions")
  public AccessorResponse<Authentication> resumeMFA(Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * An HTML responder used to handle non-standard webview requirements.
   * @param token
   * @return HTML
   */
  @GatewayAPI
  @API(description = "An HTML responder used to handle non-standard webview requirements.")
  public AccessorResponse<HtmlPage> callback(String token) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Forgot username
   * @return
   */
  @GatewayAPI
  @API(description = "Forgot Username")
  public AccessorResponse<ForgotUsername> forgotUsername() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Answer forgot username
   * @param forgotUsername
   * @return
   */
  @GatewayAPI
  @API(description = "Answer Forgot Username")
  public AccessorResponse<ForgotUsername> answerForgotUsername(ForgotUsername forgotUsername) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Reset password
   * @return
   */
  @GatewayAPI
  @API(description = "Reset Password")
  public AccessorResponse<ResetPassword> resetPassword() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Answer reset password
   * @param resetPassword
   * @return
   */
  @GatewayAPI
  @API(description = "Answer Reset Password")
  public AccessorResponse<ResetPassword> answerResetPassword(ResetPassword resetPassword) {
    throw new AccessorMethodNotImplementedException();
  }
}
