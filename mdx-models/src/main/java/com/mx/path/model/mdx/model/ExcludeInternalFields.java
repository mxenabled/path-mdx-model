package com.mx.path.model.mdx.model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.mx.path.core.common.model.Internal;

public class ExcludeInternalFields implements ExclusionStrategy {
  @Override
  public final boolean shouldSkipField(FieldAttributes f) {
    return f.getAnnotation(Internal.class) != null;
  }

  @Override
  public final boolean shouldSkipClass(Class<?> clazz) {
    return false;
  }
}
