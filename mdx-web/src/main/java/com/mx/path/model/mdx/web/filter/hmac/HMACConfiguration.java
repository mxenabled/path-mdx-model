package com.mx.path.model.mdx.web.filter.hmac;

public interface HMACConfiguration {
  /**
   * HMAC Salt used to generate signature
   * @return
   */
  default String getHmacSalt() {
    return getSalt();
  }

  /**
   * @deprecated This is not needed.
   * @return stuff we don't need
   */
  @Deprecated
  default String getHmacSHAAlgo() {
    return null;
  }

  /**
  * @deprecated Moving mitch's cheese :)
  */
  @Deprecated
  String getSalt();
}
