package com.mx.models.payment;

import com.mx.models.MdxBase;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PayeeCategory extends MdxBase<PayeeCategory> {
  private String payeeCategoryName;

  public final String getName() {
    return payeeCategoryName;
  }

  public final void setName(String name) {
    this.payeeCategoryName = name;
  }
}
