package com.mx.path.model.mdx.model.payment;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.id.MfaChallenge;

public final class Enrollment extends MdxBase<Enrollment> {
  @SerializedName("is_active")
  private Boolean isActive;

  private List<MfaChallenge> challenges;

  public Enrollment() {
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public List<MfaChallenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<MfaChallenge> challenges) {
    this.challenges = challenges;
  }
}
