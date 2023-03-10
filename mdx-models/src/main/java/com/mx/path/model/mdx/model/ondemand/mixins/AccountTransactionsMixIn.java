package com.mx.path.model.mdx.model.ondemand.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("account")
public interface AccountTransactionsMixIn {
  @JsonIgnore
  boolean getWrapped();
}
