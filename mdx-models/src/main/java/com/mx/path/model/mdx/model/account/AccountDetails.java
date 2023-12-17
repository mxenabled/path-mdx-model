package com.mx.path.model.mdx.model.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public final class AccountDetails extends MdxBase<AccountDetails> {
  private CoreFields coreFields;
  private ExtendedFields[] extendedFields;
  private Features features;

  public Features getFeatures() {
    return features;
  }

  public void setFeatures(Features features) {
    this.features = features;
  }

  public CoreFields getCoreFields() {
    return coreFields;
  }

  public void setCoreFields(CoreFields coreFields) {
    this.coreFields = coreFields;
  }

  public ExtendedFields[] getExtendedFields() {
    return extendedFields;
  }

  public void setExtendedFields(ExtendedFields[] extendedFields) {
    this.extendedFields = extendedFields;
  }
}
