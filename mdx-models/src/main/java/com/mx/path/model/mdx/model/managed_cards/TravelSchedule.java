package com.mx.path.model.mdx.model.managed_cards;

import com.mx.path.model.mdx.model.MdxBase;

public final class TravelSchedule extends MdxBase<TravelSchedule> {

  private String[] cardIds;
  private String[] destinationIds;
  private String emailAddress;
  private String endOn;
  private String id;
  private String primaryPhoneNumber;
  private String secondaryPhoneNumber;
  private String startOn;
  private String status;

  public String[] getCardIds() {
    return cardIds;
  }

  public void setCardIds(String[] newCardIds) {
    this.cardIds = newCardIds;
  }

  public String[] getDestinationIds() {
    return destinationIds;
  }

  public void setDestinationIds(String[] newDestinationIds) {
    this.destinationIds = newDestinationIds;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String newEmailAddress) {
    this.emailAddress = newEmailAddress;
  }

  public String getEndOn() {
    return endOn;
  }

  public void setEndOn(String newEndOn) {
    this.endOn = newEndOn;
  }

  public String getId() {
    return id;
  }

  public void setId(String newId) {
    this.id = newId;
  }

  public String getPrimaryPhoneNumber() {
    return primaryPhoneNumber;
  }

  public void setPrimaryPhoneNumber(String newPrimaryPhoneNumber) {
    this.primaryPhoneNumber = newPrimaryPhoneNumber;
  }

  public String getSecondaryPhoneNumber() {
    return secondaryPhoneNumber;
  }

  public void setSecondaryPhoneNumber(String newSecondaryPhoneNumber) {
    this.secondaryPhoneNumber = newSecondaryPhoneNumber;
  }

  public String getStartOn() {
    return startOn;
  }

  public void setStartOn(String newStartOn) {
    this.startOn = newStartOn;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String newStatus) {
    this.status = newStatus;
  }
}
