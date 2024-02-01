package com.mx.path.model.mdx.model.id;

import java.util.List;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public class UnlockUser extends MdxBase<UnlockUser> {
  private List<Challenge> challenges;

  public final List<Challenge> getChallenges() {
    return challenges;
  }

  public final void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }
}
