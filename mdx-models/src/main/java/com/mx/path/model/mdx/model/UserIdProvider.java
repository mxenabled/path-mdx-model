package com.mx.path.model.mdx.model;

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
}
