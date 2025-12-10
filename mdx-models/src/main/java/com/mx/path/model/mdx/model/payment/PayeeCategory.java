package com.mx.path.model.mdx.model.payment;

import com.mx.path.model.mdx.model.MdxBase;

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
