package com.mx.path.model.mdx.model;

import java.util.ArrayList;
import java.util.List;

import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.common.model.ModelBase;
import com.mx.path.core.common.model.Warning;
import com.mx.path.core.context.Session;

public abstract class MdxBase<T> extends ModelBase<T> {
  private String userId;

  private List<Warning> warnings;

  public MdxBase() {
    if (Session.current() != null && Strings.isNotBlank(Session.current().getUserId())) {
      this.setUserId(Session.current().getUserId());
    }
  }

  public final String getUserId() {
    return this.userId;
  }

  public final void setUserId(String newUserId) {
    this.userId = newUserId;
  }

  public final List<Warning> getWarnings() {
    return this.warnings;
  }

  public final void appendWarning(Warning warning) {
    if (this.warnings == null) {
      this.warnings = new ArrayList<>();
    }
    this.warnings.add(warning);
  }
}
