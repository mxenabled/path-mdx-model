package com.mx.path.model.mdx.model.challenges;

import lombok.Data;

import com.google.gson.annotations.SerializedName;
import com.mx.path.core.common.configuration.ConfigurationField;
import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class DeepLinkData extends MdxBase<DeepLinkData> {
  @ConfigurationField
  @SerializedName("deep_link")
  private String deepLink;
}
