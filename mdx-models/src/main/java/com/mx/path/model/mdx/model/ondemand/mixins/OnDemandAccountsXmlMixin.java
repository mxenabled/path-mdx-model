package com.mx.path.model.mdx.model.ondemand.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;

@JsonRootName("accounts")
public interface OnDemandAccountsXmlMixin {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JsonProperty("account")
  MdxList<Account> getAccounts();

  @JsonIgnore
  boolean getWrapped();
}
