package com.mx.path.model.mdx.model.authorization;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@Data
@EqualsAndHashCode(callSuper = true)
public class Authorization extends MdxBase<Authorization> {
  private String accountId;
  private CardManagerData cardManagerData;
  private List<Challenge> challenges;
  private NameValuePair[] cookies;
  @SerializedName("cookies_url")
  private String cookiesUrl;
  private String deviceId;
  private Long expiresAt;
  private NameValuePair[] headers;
  @SerializedName("javascript")
  private JavaScript javaScript;
  private String token;
  private NameValuePair[] tokens;
  private String type;
  private String url;
  private String vendorId;
}
