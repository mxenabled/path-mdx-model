package com.mx.path.model.mdx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.common.model.ModelBase;
import com.mx.path.core.common.model.Warning;
import com.mx.path.core.context.Session;

public abstract class MdxBase<T> extends ModelBase<T> {
  private static final Map<Class<?>, Boolean> IS_NESTED_CLASS = new ConcurrentHashMap<>();

  @Getter
  private String userId;

  private List<Warning> warnings;

  public MdxBase() {
    if (Session.current() != null && Strings.isNotBlank(Session.current().getUserId())) {
      this.setUserId(Session.current().getUserId());
    }
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

  public final void setUserId(String userId) {
    // Don't set the user ID if the model is annotated with MdxNested. Cache result to avoid reflection overhead.
    if (!IS_NESTED_CLASS.containsKey(this.getClass())) {
      IS_NESTED_CLASS.put(this.getClass(), this.getClass().isAnnotationPresent(MdxNested.class));
    }

    if (IS_NESTED_CLASS.get(this.getClass())) {
      return;
    }

    this.userId = userId;
  }
}
