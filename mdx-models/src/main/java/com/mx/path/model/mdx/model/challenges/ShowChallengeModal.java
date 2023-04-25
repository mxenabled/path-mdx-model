package com.mx.path.model.mdx.model.challenges;

import com.mx.path.model.mdx.model.MdxBase;

public final class ShowChallengeModal extends MdxBase<ShowChallengeModal> {
  private Challenge challenge;

  public Challenge getChallenge() {
    return challenge;
  }

  public void setChallenge(Challenge challenge) {
    this.challenge = challenge;
  }
}
