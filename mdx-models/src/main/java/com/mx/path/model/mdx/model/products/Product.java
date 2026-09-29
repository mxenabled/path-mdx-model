package com.mx.path.model.mdx.model.products;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public final class Product extends MdxBase<Product> {

  @SerializedName("account_data")
  private AccountData accountData;

  /**
   * @deprecated Deprecated in MDX Product Rev 6. Use {@link #challenges} instead.
   */
  @Deprecated
  @SerializedName("activate_challenges")
  private List<Challenge> activateChallenges;

  private List<Challenge> challenges;

  private String description;

  @SerializedName("expires_on")
  private String expiresOn;

  private String group;

  private String id;

  @SerializedName("image_url")
  private String imageUrl;

  @SerializedName("is_interested")
  private Boolean isInterested;

  /**
   * @deprecated Deprecated in MDX Product Rev 6. Multiple product selection is no longer
   *     supported; use {@link #status} instead.
   */
  @Deprecated
  @SerializedName("is_selected")
  private Boolean isSelected;

  private String name;

  /**
   * Status of this product. One of {@code ACTIVATING}, {@code COMPLETE}, {@code OPEN},
   * {@code EXPIRED}, {@code PENDING}.
   *
   * <p>Modelled as a String, consistent with {@link #type}, so that a status added to a later
   * revision of the spec does not break deserialization. Added in MDX Product Rev 6.
   */
  private String status;

  private String type;

  public AccountData getAccountData() {
    return accountData;
  }

  public void setAccountData(AccountData accountData) {
    this.accountData = accountData;
  }

  /**
   * @deprecated Deprecated in MDX Product Rev 6. Use {@link #getChallenges()} instead.
   * @return activate challenges
   */
  @Deprecated
  public List<Challenge> getActivateChallenges() {
    return activateChallenges;
  }

  /**
   * @deprecated Deprecated in MDX Product Rev 6. Use {@link #setChallenges(List)} instead.
   * @param activateChallenges activate challenges
   */
  @Deprecated
  public void setActivateChallenges(List<Challenge> activateChallenges) {
    this.activateChallenges = activateChallenges;
  }

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getExpiresOn() {
    return expiresOn;
  }

  public void setExpiresOn(String expiresOn) {
    this.expiresOn = expiresOn;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Boolean getIsInterested() {
    return isInterested;
  }

  public void setIsInterested(Boolean interested) {
    this.isInterested = interested;
  }

  /**
   * @deprecated Deprecated in MDX Product Rev 6. Use {@link #getStatus()} instead.
   * @return is selected
   */
  @Deprecated
  public Boolean getIsSelected() {
    return isSelected;
  }

  /**
   * @deprecated Deprecated in MDX Product Rev 6. Use {@link #setStatus(String)} instead.
   * @param isSelected is selected
   */
  @Deprecated
  public void setIsSelected(Boolean isSelected) {
    this.isSelected = isSelected;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
