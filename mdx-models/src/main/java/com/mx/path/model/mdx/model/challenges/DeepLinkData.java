package com.mx.path.model.mdx.model.challenges;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
public final class DeepLinkData extends MdxBase<DeepLinkData> {

  @SerializedName("deep_link")
  private String deepLink;

  public String getDeepLink() {
    return deepLink;
  }

  public void setDeepLink(String deepLink) {
    this.deepLink = deepLink;
  }
}
