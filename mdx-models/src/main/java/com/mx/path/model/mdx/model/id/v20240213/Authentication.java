package com.mx.path.model.mdx.model.id.v20240213;

import java.util.List;

import lombok.Data;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@Data
public class Authentication extends MdxBase<Authentication> {
  public enum DeviceRegistration {
    PRIMARY,
    SECONDARY
  }

  private final String version = "20240213";

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
  private DeviceRegistration deviceRegistration;
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

  public Authentication() {
  }

  public Authentication(String newLoginName, char[] newPassword) {
    setLogin(newLoginName);
    setPassword(newPassword);
  }

  public final Authentication withId(String newId) {
    this.id = newId;
    return this;
  }

  public final Authentication withUserId(String newUserId) {
    this.setUserId(newUserId);
    return this;
  }
}
