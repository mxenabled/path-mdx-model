package com.mx.path.model.mdx.model.payment;

import java.util.List;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.id.MfaChallenge;

public final class Settings extends MdxBase<Settings> {
  private List<MfaChallenge> challenges;

  public Settings() {
  }

  public List<MfaChallenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<MfaChallenge> challenges) {
    this.challenges = challenges;
  }
}
