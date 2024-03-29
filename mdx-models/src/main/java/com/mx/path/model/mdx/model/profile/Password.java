package com.mx.path.model.mdx.model.profile;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

/**
 * @deprecated This method is deprecated due to a change in the object serialization.
 * Use {@link NewPassword} instead.
 */
@Deprecated
public final class Password extends MdxBase<Password> {

  private List<Challenge> challenges;

  @SerializedName("current_password")
  private String currentPassword;
  @SerializedName("new_password")
  private String newPassword;

  public Password() {
  }

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public String getCurrentPassword() {
    return currentPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
