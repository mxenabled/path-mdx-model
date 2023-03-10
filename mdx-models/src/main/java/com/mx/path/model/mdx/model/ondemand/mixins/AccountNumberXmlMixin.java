package com.mx.path.model.mdx.model.ondemand.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("account_numbers")
public interface AccountNumberXmlMixin {
  @JsonIgnore
  boolean getWrapped();
}
