package com.mx.path.model.mdx.model.id;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.id.v20240213.Authentication.DeviceRegistration;

public class Authentication extends MdxBase<Authentication> {

  private String appVersion;
  @Deprecated
  private Boolean canUsePayments;
  @Deprecated
  private Boolean canUsePayouts;
  @Deprecated
  private Boolean canUseRemoteDeposit;
  private List<MfaChallenge> challenges;
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
  private String idToken;
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

  /**
   * Returns the version of the application.
   *
   * @return the application version as a {@link String}, or {@code null} if not set
   */
  public String getAppVersion() {
    return appVersion;
  }

  /**
   * Sets the version of the application.
   *
   * @param appVersion the application version to set
   */
  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public final Boolean isCanUsePayments() {
    return canUsePayments;
  }

  public final void setCanUsePayments(boolean newCanUsePayments) {
    this.canUsePayments = newCanUsePayments;
  }

  public final Boolean isCanUsePayouts() {
    return canUsePayouts;
  }

  public final void setCanUsePayouts(boolean newCanUsePayouts) {
    this.canUsePayouts = newCanUsePayouts;
  }

  public final Boolean isCanUseRemoteDeposit() {
    return canUseRemoteDeposit;
  }

  public final void setCanUseRemoteDeposit(boolean newCanUseRemoteDeposit) {
    this.canUseRemoteDeposit = newCanUseRemoteDeposit;
  }

  public final List<MfaChallenge> getChallenges() {
    return challenges;
  }

  public final void setChallenges(List<MfaChallenge> newChallenges) {
    this.challenges = newChallenges;
  }

  public final String getClientDeviceToken() {
    return clientDeviceToken;
  }

  public final void setClientDeviceToken(String clientDeviceToken) {
    this.clientDeviceToken = clientDeviceToken;
  }

  public final Integer getDeviceHeight() {
    return deviceHeight;
  }

  public final void setDeviceHeight(int newDeviceHeight) {
    this.deviceHeight = newDeviceHeight;
  }

  public final String getDeviceId() {
    return deviceId;
  }

  public final void setDeviceId(String newDeviceId) {
    this.deviceId = newDeviceId;
  }

  public final Boolean isDeviceIsJailbroken() {
    return deviceIsJailbroken;
  }

  public final void setDeviceIsJailbroken(boolean newDeviceIsJailbroken) {
    this.deviceIsJailbroken = newDeviceIsJailbroken;
  }

  public final String getDeviceMake() {
    return deviceMake;
  }

  public final void setDeviceMake(String newDeviceMake) {
    this.deviceMake = newDeviceMake;
  }

  public final String getDeviceModel() {
    return deviceModel;
  }

  public final void setDeviceModel(String newDeviceModel) {
    this.deviceModel = newDeviceModel;
  }

  public final String getDeviceName() {
    return deviceName;
  }

  public final void setDeviceName(String newDeviceName) {
    this.deviceName = newDeviceName;
  }

  public final String getDeviceOperatingSystem() {
    return deviceOperatingSystem;
  }

  public final void setDeviceOperatingSystem(String newDeviceOperatingSystem) {
    this.deviceOperatingSystem = newDeviceOperatingSystem;
  }

  public final String getDeviceOperatingSystemVersion() {
    return deviceOperatingSystemVersion;
  }

  public final void setDeviceOperatingSystemVersion(String newDeviceOperatingSystemVersion) {
    this.deviceOperatingSystemVersion = newDeviceOperatingSystemVersion;
  }

  public final Double getDeviceLatitude() {
    return deviceLatitude;
  }

  public final void setDeviceLatitude(double newDeviceLatitude) {
    this.deviceLatitude = newDeviceLatitude;
  }

  public final Double getDeviceLongitude() {
    return deviceLongitude;
  }

  public final void setDeviceLongitude(double newDeviceLongitude) {
    this.deviceLongitude = newDeviceLongitude;
  }

  public final Integer getDeviceWidth() {
    return deviceWidth;
  }

  public final void setDeviceWidth(int newDeviceWidth) {
    this.deviceWidth = newDeviceWidth;
  }

  public final void setDeviceRegistration(DeviceRegistration deviceRegistration) {
    this.deviceRegistration = deviceRegistration;
  }

  public final DeviceRegistration getDeviceRegistration() {
    return deviceRegistration;
  }

  public final String getId() {
    return id;
  }

  public final void setId(String newId) {
    this.id = newId;
  }

  public final String getIovationToken() {
    return iovationToken;
  }

  public final void setIovationToken(String iovationToken) {
    this.iovationToken = iovationToken;
  }

  public final String getIdToken() {
    return idToken;
  }

  public final void setIdToken(String idToken) {
    this.idToken = idToken;
  }

  public final String getLogin() {
    return login;
  }

  public final void setLogin(String newLogin) {
    this.login = newLogin;
  }

  public final char[] getPassword() {
    return password;
  }

  public final void setPassword(char[] password) {
    this.password = password;
  }

  public final String getRefreshToken() {
    return refreshToken;
  }

  public final void setRefreshToken(String newRefreshToken) {
    this.refreshToken = newRefreshToken;
  }

  public final String getToken() {
    return token;
  }

  public final void setToken(String newToken) {
    this.token = newToken;
  }

  public final String getAccessToken() {
    return accessToken;
  }

  public final void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public final String getUserkey() {
    return userkey;
  }

  public final void setUserkey(String userkey) {
    this.userkey = userkey;
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
