package com.mx.path.model.mdx.model.profile;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public final class Address extends MdxBase<Address> {

  @SerializedName("apply_to_all")
  private Boolean applyToAll;

  @SerializedName("account_id")
  private String accountId;

  private List<Challenge> challenges;

  @SerializedName("address_line_one")
  private String addressLineOne;
  @SerializedName("address_line_two")
  private String addressLineTwo;
  @SerializedName("address_type")
  private AddressType addressType;
  private String city;
  private String country;
  private String id;
  @SerializedName("foreign_address")
  private Boolean isForeignAddress;
  @SerializedName("postal_code")
  private String postalCode;
  private String state;

  public Address() {
  }

  public Boolean getApplyToAll() {
    return applyToAll;
  }

  public void setApplyToAll(Boolean applyToAll) {
    this.applyToAll = applyToAll;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public String getAddressLineOne() {
    return addressLineOne;
  }

  public void setAddressLineOne(String addressLineOne) {
    this.addressLineOne = addressLineOne;
  }

  public String getAddressLineTwo() {
    return addressLineTwo;
  }

  public void setAddressLineTwo(String addressLineTwo) {
    this.addressLineTwo = addressLineTwo;
  }

  public AddressType getAddressType() {
    return addressType;
  }

  public void setAddressType(AddressType addressType) {
    this.addressType = addressType;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Boolean isForeignAddress() {
    return isForeignAddress;
  }

  public void setForeignAddress(Boolean foreignAddress) {
    isForeignAddress = foreignAddress;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public enum AddressType {
    @SerializedName("MAILING")
    MAILING("Mailing Address"), @SerializedName("HOME")
    HOME("Home Address"), @SerializedName("BUSINESS")
    BUSINESS("Business Address"), @SerializedName("WORK")
    WORK("Work Address");

    private final String description;

    AddressType(String description) {
      this.description = description;
    }

    public String getDescription() {
      return this.description;
    }

  }
}
