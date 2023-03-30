package com.mx.path.service.connection.realtime.model;

import lombok.Data;

import com.google.gson.annotations.SerializedName;

@Data
public class MdxMemberWrapper {
  @SerializedName("member")
  private MdxMember member;
}
