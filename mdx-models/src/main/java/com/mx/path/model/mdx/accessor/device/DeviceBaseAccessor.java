package com.mx.path.model.mdx.accessor.device;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;

/**
 * Device base operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/device/#mdx-device">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/device/#mdx-device")
public abstract class DeviceBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private VerificationMethodsBaseAccessor verificationMethods;

  public DeviceBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public DeviceBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Accessor for transfer account operations
   *
   * @return accessor
   */
  @API(description = "Access transfer verificationMethods")
  public VerificationMethodsBaseAccessor verificationMethods() {
    if (verificationMethods != null) {
      return verificationMethods;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set device verification methods accessor
   *
   * @param verificationMethods
   */
  public void setVerificationMethods(VerificationMethodsBaseAccessor verificationMethods) {
    this.verificationMethods = verificationMethods;
  }
}
