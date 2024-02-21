package com.mx.path.model.mdx.model.account.alerts;

import java.time.LocalDate;
import java.util.List;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public class AccountAlert extends MdxBase<AccountAlert> {
  private List<Challenge> challenges;
  private Long createdAt;
  private LocalDate createdOn;
  private List<AlertCriteria> criteria;
  private List<String> deliveryTargets;
  private String description;
  private String id;
  private boolean isEnabled;
  private String name;
  private String type;

  public final List<Challenge> getChallenges() {
    return challenges;
  }

  public final void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public final Long getCreatedAt() {
    return createdAt;
  }

  public final void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public final LocalDate getCreatedOn() {
    return createdOn;
  }

  public final void setCreatedOn(LocalDate createdOn) {
    this.createdOn = createdOn;
  }

  public final List<AlertCriteria> getCriteria() {
    return criteria;
  }

  public final void setCriteria(List<AlertCriteria> criteria) {
    this.criteria = criteria;
  }

  public final List<String> getDeliveryTargets() {
    return deliveryTargets;
  }

  public final void setDeliveryTargets(List<String> deliveryTargets) {
    this.deliveryTargets = deliveryTargets;
  }

  public final String getDescription() {
    return description;
  }

  public final void setDescription(String description) {
    this.description = description;
  }

  public final String getId() {
    return id;
  }

  public final void setId(String id) {
    this.id = id;
  }

  public final boolean isEnabled() {
    return isEnabled;
  }

  public final void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }

  public final String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public final String getType() {
    return type;
  }

  public final void setType(String type) {
    this.type = type;
  }
}
