package com.mx.path.model.mdx.accessor.device;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.device.VerificationMethod;

/**
 * Accessor base for device verification method operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/device/#verification-methods">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/device/#verification-methods")
public abstract class VerificationMethodsBaseAccessor extends Accessor {

  public VerificationMethodsBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public VerificationMethodsBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  @GatewayAPI
  @API(description = "Get all user's verification methods")
  public AccessorResponse<MdxList<VerificationMethod>> list() {
    throw new AccessorMethodNotImplementedException();
  }
}
