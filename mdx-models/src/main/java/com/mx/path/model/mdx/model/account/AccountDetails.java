package com.mx.path.model.mdx.model.account;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@EqualsAndHashCode(callSuper = true)
@Data
public final class AccountDetails extends MdxBase<AccountDetails> {
  private CoreFields coreFields;
  private List<ExtendedFields> extendedFields;
  private Features features;
}
