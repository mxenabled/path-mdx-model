package com.mx.path.model.mdx.model.transfer.options;

import lombok.Data;

import com.google.gson.annotations.SerializedName;

@Data
public class FeeListOptions {
  private Number amount;
  @SerializedName("from_account_id")
  private String fromAccountId;
  @SerializedName("to_account_id")
  private String toAccountId;
}
