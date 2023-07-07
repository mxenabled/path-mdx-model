package com.mx.path.model.mdx.model;

import java.util.List;

import com.mx.path.core.common.model.ModelList;

/**
 * List of MdxBase objects
 *
 * @param <T>
 */
public class MdxList<T extends MdxBase<?>> extends ModelList<T> {
  public MdxList() {
    super();
  }

  public MdxList(List<? extends T> copyList) {
    super(copyList);
  }

  /**
   * Marks list as wrapped
   * Override to modify wrapping behavior
   *
   * @return wrapped T
   */
  @Override
  public MdxList<T> wrapped() {
    setWrapped(true);
    return this;
  }
}
