package com.mx.path.model.mdx.model.p2p_transfer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
@Data
@EqualsAndHashCode(callSuper = true)
public class DebitCardData extends MdxBase<DebitCardData> {
  private String description;
  private String lastFour;
}
