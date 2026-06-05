package com.mx.path.model.mdx.model.ondemand.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;

import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonRootName("accounts")
public interface OnDemandAccountsXmlMixin {
  @JacksonXmlElementWrapper(useWrapping = false)
  @JsonProperty("account")
  MdxList<Account> getAccounts();

  @JsonIgnore
  boolean getWrapped();
}
