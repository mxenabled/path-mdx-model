package com.mx.path.model.mdx.accessor.credit_report;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
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
