package com.mx.path.model.mdx.model.challenges;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.authorization.NameValuePair;

public final class HtmlData extends MdxBase<HtmlData> {

  private NameValuePair[] cookies;

  private NameValuePair[] headers;

  private String html;

  private String url;

  public NameValuePair[] getCookies() {
    return cookies;
  }

  public void setCookies(NameValuePair[] cookies) {
    this.cookies = cookies;
  }

  public NameValuePair[] getHeaders() {
    return headers;
  }

  public void setHeaders(NameValuePair[] headers) {
    this.headers = headers;
  }

  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
