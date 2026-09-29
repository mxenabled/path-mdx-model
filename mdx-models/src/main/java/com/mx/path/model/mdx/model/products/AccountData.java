package com.mx.path.model.mdx.model.products;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

/**
 * Additional information about the account that a product will create.
 *
 * <p>Supplied on products of type {@code ACCOUNT}. Added in MDX Product Rev 6.
 */
@MdxNested
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountData extends MdxBase<AccountData> {
  private String accountType;
}
