package com.mx.path.model.mdx.model.challenges;

import lombok.Data;

import com.google.gson.annotations.SerializedName;
import com.mx.path.core.common.configuration.ConfigurationField;
import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class Action extends MdxBase<Action> {
  @ConfigurationField
  private String id;

  @ConfigurationField
  private String type;

  @ConfigurationField
  @SerializedName("show_challenge_modal_data")
  private ShowChallengeModal showChallengeModalData;

  @ConfigurationField
  @SerializedName("show_status_data")
  private ShowStatus showStatusData;

  @ConfigurationField
  @SerializedName("deep_link_data")
  private DeepLinkData deepLinkData;
}
