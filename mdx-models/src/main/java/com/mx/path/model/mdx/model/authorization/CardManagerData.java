package com.mx.path.model.mdx.model.authorization;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
@Data
@EqualsAndHashCode(callSuper = true)
public class CardManagerData extends MdxBase<CardManagerData> {
  private String authUserId;
  private List<CardDetail> cardDetails;
}
