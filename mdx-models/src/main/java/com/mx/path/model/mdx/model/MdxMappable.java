package com.mx.path.model.mdx.model;

import java.util.List;

public abstract class MdxMappable<T extends MdxBase<T>> {
  public abstract T map() throws MdxMappingException;

  public static <TO extends MdxBase<TO>, FROM extends MdxMappable<TO>> MdxList<TO> map(List<FROM> list, Class<TO> mapTo) throws MdxMappingException {
    MdxList<TO> result = new MdxList<>();
    for (FROM mappable : list) {
      result.add(mappable.map());
    }

    return result;
  }
}
