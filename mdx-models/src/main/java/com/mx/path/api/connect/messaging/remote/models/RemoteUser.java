package com.mx.path.connect.messaging.remote.models;

import java.util.List;

import lombok.Data;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.profile.Phone;

@Data
public class RemoteUser extends MdxBase<RemoteUser> {
  private String accountId;
  private String address1;
  private String address2;
  private Boolean billPayEligible;
  private Boolean billPayEnrolled;
  private String birthDate;
  private Boolean businessUser;
  private String checkfreeSubscriberId;
  private String city;
  private String email;
  private String firstName;
  private String lastName;
  private String phone;
  private List<Phone> phones;
  private Boolean remoteDepositEligible;
  private Boolean remoteDepositEnrolled;
  private String ssn;
  private String state;
  private String postalCode;
  private String newUserName;
  private String currentPassword;
  private String newPassword;
  //Adding this to support origination end point for update user when we get clientId once everything is verified
  private String clientUserId;
  private String otpVerificationCode;
  //Adding this to support different IDP states which connectors may need
  private IDPState idpState;
  //Adding this to support creditbureau Application Number
  private String creditBureauApplicationNumber;
}
