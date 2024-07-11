package com.mx.path.model.mdx.model.challenges;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
public final class UrlData extends MdxBase<UrlData> {

  @SerializedName("type")
  private String type;

  @SerializedName("url")
  private String url;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
