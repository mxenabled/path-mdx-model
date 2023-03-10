package com.mx.path.model.mdx.model;

import java.util.function.Supplier;

import com.mx.common.models.MdxBase;
import com.mx.path.model.context.Session;

/**
 * User ID Provider
 *
 * This static class uses the current session to the current user's id and put it on provided objects.
 * This provider is called in the constructor of Mdx Models that need a user id.
 */
public class UserIdProvider {
  public static void setUserId(MdxBase<?> obj) {
    if (Session.current() != null) {
      obj.setUserId(Session.current().getUserId());
    }
  }

  /**
   * @deprecated This no longer any affect. Now uses Session.current()
   * todo: V2 - remove
   */
  @Deprecated
  public static void setUserIdProvider(Supplier<String> newUserIdProvider) {
  }
}
