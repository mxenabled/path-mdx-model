package com.mx.path.model.mdx.model.challenges;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
public final class Text extends MdxBase<Text> {
  private String characterType;

  public String getCharacterType() {
    return characterType;
  }

  public void setCharacterType(String characterType) {
    this.characterType = characterType;
  }
}
