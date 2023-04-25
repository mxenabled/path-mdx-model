package com.mx.path.model.mdx.model.payout;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.UserIdProvider;

public final class PayoutSettings extends MdxBase<PayoutSettings> {

  private String id;
  private String emailAddress;
  private String payoutContactMethodType;
  private String status;

  private PayoutSettings() {
    UserIdProvider.setUserId(this);
  }

  public String getId() {
    return id;
  }

  public void setId(String newId) {
    this.id = newId;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String newEmailAddress) {
    this.emailAddress = newEmailAddress;
  }

  public String getPayoutContactMethodType() {
    return payoutContactMethodType;
  }

  public void setPayoutContactMethodType(String newPayoutContactMethodType) {
    this.payoutContactMethodType = newPayoutContactMethodType;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String newStatus) {
    this.status = newStatus;
  }
}
