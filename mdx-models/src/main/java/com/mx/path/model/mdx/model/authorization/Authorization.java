package com.mx.path.model.mdx.model.authorization;

import java.util.List;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public class Authorization extends MdxBase<Authorization> {

  private String accountId;
  private List<Challenge> challenges;
  private NameValuePair[] cookies;
  private String deviceId;
  private Long expiresAt;
  private NameValuePair[] headers;
  private JavaScript javascript;
  private String token;
  private NameValuePair[] tokens;
  private String type;
  private String url;
  private String vendorId;

  public Authorization() {
  }

  public final String getAccountId() {
    return accountId;
  }

  public final void setAccountId(String newAccountId) {
    accountId = newAccountId;
  }

  public final List<Challenge> getChallenges() {
    return challenges;
  }

  public final void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public final NameValuePair[] getCookies() {
    return cookies;
  }

  public final void setCookies(NameValuePair[] newCookies) {
    this.cookies = newCookies;
  }

  public final String getDeviceId() {
    return deviceId;
  }

  public final void setDeviceId(String newDeviceId) {
    accountId = newDeviceId;
  }

  public final Long getExpiresAt() {
    return expiresAt;
  }

  public final void setExpiresAt(Long newExpiresAt) {
    this.expiresAt = newExpiresAt;
  }

  public final NameValuePair[] getHeaders() {
    return headers;
  }

  public final void setHeaders(NameValuePair[] newHeaders) {
    headers = newHeaders;
  }

  public final JavaScript getJavaScript() {
    return javascript;
  }

  public final void setJavaScript(JavaScript newJavaScript) {
    this.javascript = newJavaScript;
  }

  public final String getToken() {
    return token;
  }

  public final void setToken(String newToken) {
    this.token = newToken;
  }

  public final NameValuePair[] getTokens() {
    return tokens;
  }

  public final void setTokens(NameValuePair[] tokens) {
    this.tokens = tokens;
  }

  public final String getType() {
    return type;
  }

  public final void setType(String newType) {
    this.type = newType;
  }

  public final String getUrl() {
    return url;
  }

  public final void setUrl(String newUrl) {
    url = newUrl;
  }

  public final String getVendorId() {
    return vendorId;
  }

  public final void setVendorId(String newVendorId) {
    accountId = newVendorId;
  }
}
