package com.mx.path.model.mdx.model.p2p_transfer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@Data
@EqualsAndHashCode(callSuper = true)
public class Duration extends MdxBase<Duration> {
  private String description;
  private String name;
  private String type;
}
