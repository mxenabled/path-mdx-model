package com.mx.testing;

import com.mx.accessors.id.IdBaseAccessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorResponse;
import com.mx.models.id.Authentication;

public class IdAccessorImpl extends IdBaseAccessor {
  public IdAccessorImpl(AccessorConfiguration configuration) {
    super(configuration);
  }

  @Override
  public AccessorResponse<Authentication> authenticate(Authentication authentication) {
    return super.authenticate(authentication);
  }
}
