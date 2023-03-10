package com.mx.path.model.mdx.model.ondemand.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("account_owner")
public interface AccountOwnerDetailsXmlMixin {
  @JsonIgnore
  boolean getWrapped();
}
