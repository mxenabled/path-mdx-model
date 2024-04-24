package com.mx.path.model.mdx.model.products;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public final class Product extends MdxBase<Product> {

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

  @SerializedName("is_selected")
  private Boolean isSelected;

  private String name;

  @SerializedName("product_type")
  private String productType;

  public List<Challenge> getActivateChallenges() {
    return activateChallenges;
  }

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

  public Boolean getIsSelected() {
    return isSelected;
  }

  public void setIsSelected(Boolean isSelected) {
    this.isSelected = isSelected;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

}
