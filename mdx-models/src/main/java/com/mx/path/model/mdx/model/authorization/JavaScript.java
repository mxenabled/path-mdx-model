package com.mx.path.model.mdx.model.authorization;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
public class JavaScript extends MdxBase<JavaScript> {

  private NameValuePair[] arguments;
  private String functionToInvoke;

  public final NameValuePair[] getArguments() {
    return arguments;
  }

  public final void setArguments(NameValuePair[] newArguments) {
    this.arguments = newArguments;
  }

  public final String getFunctionToInvoke() {
    return functionToInvoke;
  }

  public final void setFunctionToInvoke(String newFunctionToInvoke) {
    this.functionToInvoke = newFunctionToInvoke;
  }
}
