package com.mx.models.authorization;

import lombok.Data;

import com.mx.common.models.MdxBase;

@Data
public class HtmlPage extends MdxBase<HtmlPage> {

  private String content;

}
