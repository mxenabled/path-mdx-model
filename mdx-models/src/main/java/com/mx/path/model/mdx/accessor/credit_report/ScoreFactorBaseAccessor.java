package com.mx.path.model.mdx.accessor.credit_report;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.credit_report.CreditReportScoreFactor;

/**
 * Accessor base for credit report operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/credit_report/#credit-report-score-factors")
public abstract class ScoreFactorBaseAccessor extends Accessor {

  public ScoreFactorBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a score factor
   * @param reportId
   * @param factorId
   * @return
   */
  @GatewayAPI
  @API(description = "Get a score factor")
  public AccessorResponse<CreditReportScoreFactor> get(String reportId, String factorId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List score factors
   * @param reportId
   * @return
   */
  @GatewayAPI
  @API(description = "List score factors")
  public AccessorResponse<MdxList<CreditReportScoreFactor>> list(String reportId) {
    throw new AccessorMethodNotImplementedException();
  }

}
