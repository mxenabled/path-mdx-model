package com.mx.path.model.mdx.model.id.v20240209;

import java.util.List;

import lombok.Data;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@Data
public class Authentication20240209 extends MdxBase<Authentication20240209> {
  private final String version = "20240209";

  private List<Challenge> challenges;
  private String clientDeviceToken;
  private Integer deviceHeight;
  private String deviceId;
  private Boolean deviceIsJailbroken;
  private String deviceMake;
  private String deviceModel;
  private String deviceName;
  private String deviceOperatingSystem;
  private String deviceOperatingSystemVersion;
  private Double deviceLatitude;
  private Double deviceLongitude;
  private Integer deviceWidth;
  private String id;
  @SerializedName("device_iovation_token")
  private String iovationToken;
  private String login;
  private char[] password;
  private String refreshToken;
  private String accessToken;
  private String token;
  private String userkey;

  public Authentication20240209() {
  }

  public Authentication20240209(String newLoginName, char[] newPassword) {
    setLogin(newLoginName);
    setPassword(newPassword);
  }

  public final Authentication20240209 withId(String newId) {
    this.id = newId;
    return this;
  }

  public final Authentication20240209 withUserId(String newUserId) {
    this.setUserId(newUserId);
    return this;
  }
}
