package com.mx.path.model.mdx.model.managed_cards;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.gson.annotations.SerializedName;
import com.mx.path.core.common.model.Internal;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@Data
@EqualsAndHashCode(callSuper = true)
public final class ManagedCard extends MdxBase<ManagedCard> {

  private String accountId;
  private String accountType;
  private Boolean allowPushNotification;
  private String expirationOnCard;
  private String id;
  private String imageUrl;
  private String issuanceType;
  private String maskedNumberOnCard;
  private String name;
  private String nameOnCard;
  private String pin;
  private String newPin;
  private String status;
  private String statusMessage;
  private String type;
  private String unmaskedNumberOnCard;
  @SerializedName("cvv")
  private String unmaskedCvv;
  private Challenge[] challenges;

  // --------------------------------------------------------
  // Internal Fields
  //  ** These fields will not render in web responses.
  //  ** They are only for internal communication.
  // --------------------------------------------------------
  @Internal
  @SerializedName("card_number")
  private String cardNumber;
  @Internal
  @SerializedName("ondot_ref_id")
  private String ondotRefId;
}
