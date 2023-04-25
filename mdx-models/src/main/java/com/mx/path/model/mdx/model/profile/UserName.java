package com.mx.path.model.mdx.model.profile;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;

public class UserName extends MdxBase<UserName> {
  @SerializedName("new_username")
  private String newUserName;

  public final String getNewUserName() {
    return newUserName;
  }

  public final void setNewUserName(String newUserName) {
    this.newUserName = newUserName;
  }
}
