package com.mx.path.model.mdx.accessor.id;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.accessor.PathResponseStatus;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.core.common.remote.RemoteOperation;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.authorization.HtmlPage;
import com.mx.path.model.mdx.model.id.Authentication;
import com.mx.path.model.mdx.model.id.ForgotUsername;
import com.mx.path.model.mdx.model.id.ResetPassword;
import com.mx.path.model.mdx.model.id.UnlockUser;

/**
 * Accessor for id operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/id/#mdx-id">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/id/#mdx-id")
public abstract class IdBaseAccessor extends Accessor {
  public IdBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public IdBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Start Authenticate
   *
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "start Authentication process by sending if login should be webview or normal login")
  public AccessorResponse<Authentication> startAuthentication(Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Start Authenticate
   *
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "start Authentication process by sending if login should be webview or normal login", version = "20240213")
  @RemoteOperation("startAuthentication20240213")
  public AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication> startAuthentication(com.mx.path.model.mdx.model.id.v20240213.Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Authenticate session
   *
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
   *
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "Authenticate user version 20240213")
  @RemoteOperation("authenticate20240213")
  public AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication> authenticate(com.mx.path.model.mdx.model.id.v20240213.Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Authenticate session
   *
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
   *
   * <p>Default implementation does nothing and returns a 204
   */
  @GatewayAPI
  @API(description = "Terminate the session")
  public AccessorResponse<Void> logout(String sessionId) {
    return AccessorResponse.<Void>builder().status(PathResponseStatus.NO_CONTENT).build();
  }

  /**
   * Resume MFA Session
   *
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "Respond to MFA questions")
  public AccessorResponse<Authentication> resumeMFA(Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Resume MFA Session
   *
   * @param authentication
   * @return
   */
  @GatewayAPI
  @API(description = "Respond to MFA questions", version = "20240213")
  @RemoteOperation("resumeMFA20240213")
  public AccessorResponse<com.mx.path.model.mdx.model.id.v20240213.Authentication> resumeMFA(com.mx.path.model.mdx.model.id.v20240213.Authentication authentication) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * An HTML responder used to handle non-standard webview requirements.
   *
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
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Forgot Username")
  public AccessorResponse<ForgotUsername> forgotUsername() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Answer forgot username
   *
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
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Reset Password")
  public AccessorResponse<ResetPassword> resetPassword() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Answer reset password
   *
   * @param resetPassword
   * @return
   */
  @GatewayAPI
  @API(description = "Answer Reset Password")
  public AccessorResponse<ResetPassword> answerResetPassword(ResetPassword resetPassword) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Unlock user
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Unlock User")
  public AccessorResponse<UnlockUser> unlockUser(UnlockUser unlockUser) {
    throw new AccessorMethodNotImplementedException();
  }
}
