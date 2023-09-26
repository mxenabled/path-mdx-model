package com.mx.path.model.mdx.model.payment;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public final class Enrollment extends MdxBase<Enrollment> {
  @SerializedName("is_active")
  private Boolean isActive;

  private List<Challenge> challenges;

  public Enrollment() {
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean active) {
    isActive = active;
  }

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }
}
