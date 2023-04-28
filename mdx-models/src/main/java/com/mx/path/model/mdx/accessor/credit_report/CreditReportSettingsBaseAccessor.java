package com.mx.path.model.mdx.accessor.credit_report;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.credit_report.CreditReportSettings;

/**
 * Accessor base for credit report settings operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/credit_report/#mdx-credit-reports")
public abstract class CreditReportSettingsBaseAccessor extends Accessor {

  public CreditReportSettingsBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get credit report settings
   * @return
   */
  @GatewayAPI
  @API(description = "Get credit report settings")
  public AccessorResponse<CreditReportSettings> get() {
    throw new AccessorMethodNotImplementedException();
  }

}
