package com.mx.path.model.mdx.model.profile;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public final class Email extends MdxBase<Email> {

  private List<Challenge> challenges;
  @SerializedName("email_address")
  private String emailAddress;
  @SerializedName("email_type")
  private EmailType emailType;
  private String id;
  @SerializedName("rank")
  private Rank rank;

  public Email() {
  }

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public EmailType getEmailType() {
    return emailType;
  }

  public void setEmailType(EmailType emailType) {
    this.emailType = emailType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  public enum EmailType {
    BUSINESS, PERSONAL
  }

  public enum Rank {
    PRIMARY, SECONDARY, TERTIARY
  }
}
