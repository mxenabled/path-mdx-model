package com.mx.path.model.mdx.model.challenges;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
public final class Action extends MdxBase<Action> {
  private String id;
  private String type;
  @SerializedName("show_challenge_modal_data")
  private ShowChallengeModal showChallengeModalData;
  @SerializedName("show_status_data")
  private ShowStatus showStatusData;
  @SerializedName("deep_link_data")
  private DeepLinkData deepLinkData;
  @SerializedName("url_data")
  private UrlData urlData;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ShowChallengeModal getShowChallengeModalData() {
    return showChallengeModalData;
  }

  public void setShowChallengeModalData(ShowChallengeModal showChallengeModalData) {
    this.showChallengeModalData = showChallengeModalData;
  }

  public ShowStatus getShowStatusData() {
    return showStatusData;
  }

  public void setShowStatusData(ShowStatus showStatusData) {
    this.showStatusData = showStatusData;
  }

  public DeepLinkData getDeepLinkData() {
    return deepLinkData;
  }

  public void setDeepLinkData(DeepLinkData deepLinkData) {
    this.deepLinkData = deepLinkData;
  }

  public UrlData getUrlData() {
    return urlData;
  }

  public void setUrlData(UrlData urlData) {
    this.urlData = urlData;
  }
}
