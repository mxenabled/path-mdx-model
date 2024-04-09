package com.mx.path.model.mdx.model.profile;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public final class Phone extends MdxBase<Phone> {
  private String id;
  private List<Challenge> challenges;
  @SerializedName("phone_number")
  private String phoneNumber;
  @SerializedName("phone_type")
  private String phoneType;
  @SerializedName("rank")
  private String rank;
  @SerializedName("work_extension")
  private String workExtension;

  public Phone() {
  }

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(String phoneType) {
    this.phoneType = phoneType;
  }

  public String getRank() {
    return rank;
  }

  public void setRank(String rank) {
    this.rank = rank;
  }

  public String getWorkExtension() {
    return workExtension;
  }

  public void setWorkExtension(String workExtension) {
    this.workExtension = workExtension;
  }

  public enum PhoneType {
    MOBILE, HOME, WORK, FOREIGN
  }

  public enum Rank {
    PRIMARY, SECONDARY, TERTIARY
  }
}
