package com.mx.path.api.lib.realtime.models;

import lombok.Data;

import com.google.gson.annotations.SerializedName;

/**
 * @deprecated Use {@link com.mx.path.service.connection.realtime.model.MdxUserWrapper}
 */
@Deprecated
@Data
public class MdxUserWrapper {
  @SerializedName("user")
  private MdxUser user;
}
