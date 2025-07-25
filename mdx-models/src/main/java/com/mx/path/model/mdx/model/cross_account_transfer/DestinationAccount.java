package com.mx.path.model.mdx.model.cross_account_transfer;

import com.mx.path.model.mdx.model.MdxBase;

public class DestinationAccount extends MdxBase<DestinationAccount> {

  private String accountHolder;
  private String accountNumber;
  private String accountType;
  private String accountTypeId;
  private String accountTypeSubtype;
  private Integer accountTypeNumber;
  private String id;
  private String nickname;

  public DestinationAccount() {
  }

  public final String getAccountHolder() {
    return accountHolder;
  }

  public final void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }

  public final String getAccountNumber() {
    return accountNumber;
  }

  public final void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public final String getAccountType() {
    return accountType;
  }

  public final void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public final String getAccountTypeId() {
    return accountTypeId;
  }

  public final void setAccountTypeId(String accountTypeId) {
    this.accountTypeId = accountTypeId;
  }

  public final String getAccountTypeSubtype() {
    return accountTypeSubtype;
  }

  public final void setAccountTypeSubtype(String accountTypeSubtype) {
    this.accountTypeSubtype = accountTypeSubtype;
  }

  public final Integer getAccountTypeNumber() {
    return accountTypeNumber;
  }

  public final void setAccountTypeNumber(Integer accountTypeNumber) {
    this.accountTypeNumber = accountTypeNumber;
  }

  public final String getId() {
    return id;
  }

  public final void setId(String id) {
    this.id = id;
  }

  public final String getNickname() {
    return nickname;
  }

  public final void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
