package com.mx.path.model.mdx.model.authorization;

import lombok.Data;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
@Data
public class HtmlPage extends MdxBase<HtmlPage> {

  private String content;

}
