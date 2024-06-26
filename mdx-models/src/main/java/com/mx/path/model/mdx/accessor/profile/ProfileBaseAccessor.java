package com.mx.path.model.mdx.accessor.profile;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.challenges.Challenge;
import com.mx.path.model.mdx.model.profile.NewPassword;
import com.mx.path.model.mdx.model.profile.NewUserName;
import com.mx.path.model.mdx.model.profile.Password;
import com.mx.path.model.mdx.model.profile.Profile;
import com.mx.path.model.mdx.model.profile.UserName;

/**
 * Accessor base for profiles
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/profile/#profile">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#profile")
public class ProfileBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private AddressBaseAccessor addresses;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private ChallengeQuestionBaseAccessor challengeQuestions;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private SecurityQuestionBaseAccessor securityQuestions;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private EmailBaseAccessor emails;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private PhoneBaseAccessor phones;

  public ProfileBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public ProfileBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Accessor for address operations
   *
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#addresses")
  public AddressBaseAccessor addresses() {
    if (addresses != null) {
      return addresses;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set addresses accessor
   *
   * @param addresses
   */
  public void setAddresses(AddressBaseAccessor addresses) {
    this.addresses = addresses;
  }

  /**
   * Accessor for challenge question operations
   *
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/index.html#challenge-questions")
  public ChallengeQuestionBaseAccessor challengeQuestions() {
    if (challengeQuestions != null) {
      return challengeQuestions;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set challenge questions accessor
   *
   * @param challengeQuestions
   */
  public void setChallengeQuestions(ChallengeQuestionBaseAccessor challengeQuestions) {
    this.challengeQuestions = challengeQuestions;
  }

  /**
   * Accessor for security question operations
   *
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/index.html#security-questions")
  public SecurityQuestionBaseAccessor securityQuestions() {
    if (securityQuestions != null) {
      return securityQuestions;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set security questions accessor
   *
   * @param securityQuestions
   */
  public void setSecurityQuestions(SecurityQuestionBaseAccessor securityQuestions) {
    this.securityQuestions = securityQuestions;
  }

  /**
   * Accessor for email operations
   *
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#addresses")
  public EmailBaseAccessor emails() {
    if (emails != null) {
      return emails;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set emails accessor
   *
   * @param emails
   */
  public void setEmails(EmailBaseAccessor emails) {
    this.emails = emails;
  }

  /**
   * Accessor for phone operations
   *
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/profile/#addresses")
  public PhoneBaseAccessor phones() {
    if (phones != null) {
      return phones;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * set ohones accessor
   *
   * @param phones
   */
  public void setPhones(PhoneBaseAccessor phones) {
    this.phones = phones;
  }

  /**
   * Get user's profile
   *
   * @return
   */
  @GatewayAPI
  @API(description = "Get user's profile")
  public AccessorResponse<Profile> get() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update user's profile
   *
   * @param profile
   * @return
   */
  @GatewayAPI
  @API(description = "Update user's profile")
  @Deprecated
  public AccessorResponse<Profile> update(Profile profile) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update user's user name
   *
   * @param userName
   * @return
   *
   * @deprecated This method is deprecated to allow a better interface with mfa challenges.
   * Use {@link #updateUserNameWithMFA} instead.
   */
  @Deprecated
  @GatewayAPI
  @API(description = "Update user's user name")
  public AccessorResponse<Void> updateUserName(UserName userName) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update user's password
   *
   * @param password
   * @return
   *
   * @deprecated This method is deprecated to allow a better interface with mfa challenges.
   * Use {@link #updatePasswordWithMFA} instead.
   */
  @Deprecated
  @GatewayAPI
  @API(description = "Update user's password")
  public AccessorResponse<MdxList<Challenge>> updatePassword(Password password) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update user's password
   *
   * @param challengeId
   * @param challenge
   * @return
   */
  @GatewayAPI
  @API(description = "Update user's password")
  public AccessorResponse<MdxList<Challenge>> updatePasswordResume(String challengeId, Challenge challenge) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update user's user name with mfa
   *
   * @param userName
   * @return
   */
  @GatewayAPI
  @API(description = "Update user's user name with mfa")
  public AccessorResponse<NewUserName> updateUserNameWithMFA(NewUserName userName) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update user's password with mfa
   *
   * @param password
   * @return
   */
  @GatewayAPI
  @API(description = "Update user's password with mfa")
  public AccessorResponse<NewPassword> updatePasswordWithMFA(NewPassword password) {
    throw new AccessorMethodNotImplementedException();
  }

}
