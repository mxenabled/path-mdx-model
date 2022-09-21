package com.mx.models.challenges;

import com.mx.common.models.MdxBase;

public final class Text extends MdxBase<Text> {
  private String characterType;

  public String getCharacterType() {
    return characterType;
  }

  public void setCharacterType(String characterType) {
    this.characterType = characterType;
  }
}
