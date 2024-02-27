package com.mx.path.model.mdx.model.documents;

import java.util.List;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

public final class DeliveryPreferences extends MdxBase<DeliveryPreferences> {
  private String accountId;
  private List<Challenge> preferences;
  private String documentType;

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public List<Challenge> getPreferences() {
    return preferences;
  }

  public void setPreferences(List<Challenge> preferences) {
    this.preferences = preferences;
  }

  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }
}
