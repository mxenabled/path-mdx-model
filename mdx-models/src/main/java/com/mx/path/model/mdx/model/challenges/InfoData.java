package com.mx.path.model.mdx.model.challenges;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
public final class InfoData extends MdxBase<InfoData> {
  private Format format;

  public Format getFormat() {
    return format;
  }

  public void setFormat(Format format) {
    this.format = format;
  }
}
