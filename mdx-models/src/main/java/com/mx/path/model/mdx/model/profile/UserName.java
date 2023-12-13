package com.mx.path.model.mdx.model.profile;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public class UserName extends MdxBase<UserName> {

  private List<Challenge> challenges;

  @SerializedName("new_username")
  private String newUserName;

  public final List<Challenge> getChallenges() {
    return challenges;
  }

  public final void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public final String getNewUserName() {
    return newUserName;
  }

  public final void setNewUserName(String newUserName) {
    this.newUserName = newUserName;
  }
}
