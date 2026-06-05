package com.mx.path.model.mdx.model.ondemand.mixins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Transaction;

import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public interface TransactionsPageMixin {

  @JacksonXmlProperty(isAttribute = true)
  String getPage();

  @JacksonXmlProperty(isAttribute = true)
  String getPages();

  @JacksonXmlProperty(isAttribute = true)
  String getStartDate();

  @JacksonXmlElementWrapper(useWrapping = false)
  @JsonProperty("transaction")
  MdxList<Transaction> getTransactions();

  @JsonIgnore
  boolean getWrapped();

}
