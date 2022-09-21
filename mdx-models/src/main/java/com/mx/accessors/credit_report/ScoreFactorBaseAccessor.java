package com.mx.accessors.credit_report;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.credit_report.CreditReportScoreFactor;

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
