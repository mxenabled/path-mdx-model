package com.mx.path.model.mdx.model.profile;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@EqualsAndHashCode(callSuper = true)
@Data
public final class ChallengeQuestions extends MdxBase<ChallengeQuestions> {
  private List<Challenge> challenges;

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }
}
