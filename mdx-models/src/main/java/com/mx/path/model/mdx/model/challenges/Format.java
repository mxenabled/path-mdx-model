package com.mx.path.model.mdx.model.challenges;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;

public final class Format extends MdxBase<Format> {
  private String font;
  private String color;

  @SerializedName("all_upper")
  private boolean allUpper;

  public String getFont() {
    return font;
  }

  public String getColor() {
    return color;
  }

  public boolean isAllUpper() {
    return allUpper;
  }

  public void setFont(String font) {
    this.font = font;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setAllUpper(boolean allUpper) {
    this.allUpper = allUpper;
  }
}
