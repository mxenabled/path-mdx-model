package com.mx.path.model.mdx.model.ondemand.mixins;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.mx.path.model.mdx.model.ondemand.MdxOnDemandLocalDateSerializer;

import tools.jackson.databind.annotation.JsonSerialize;

@JsonRootName("account")
public interface AccountXmlMixin {

  @JsonIgnore
  String getUserId();

  @JsonIgnore
  boolean getWrapped();

  @JsonSerialize(using = MdxOnDemandLocalDateSerializer.class)
  LocalDate getPaymentDueOn();

}
