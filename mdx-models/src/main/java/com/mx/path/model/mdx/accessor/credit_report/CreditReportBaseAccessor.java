package com.mx.path.model.mdx.accessor.credit_report;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.credit_report.CreditReport;
import com.mx.path.model.mdx.model.credit_report.CreditReportSettings;

/**
 * Accessor base for credit report operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/credit_report/#mdx-credit-reports">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/credit_report/#mdx-credit-reports")
public abstract class CreditReportBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private ScoreFactorBaseAccessor scoreFactors;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private CreditReportSettingsBaseAccessor settings;

  public CreditReportBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a credit report
   * @param reportId
   * @return
   */
  @GatewayAPI
  @API(description = "Get a credit report")
  public AccessorResponse<CreditReport> get(String reportId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List credit reports
   * @return
   */
  @GatewayAPI
  @API(description = "List credit reports")
  public AccessorResponse<MdxList<CreditReport>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set score factor accessor
   * @param scoreFactors
   */
  public void setScoreFactors(ScoreFactorBaseAccessor scoreFactors) {
    this.scoreFactors = scoreFactors;
  }

  /**
   * Accessor for transfer operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/id/#mdx-id")
  public ScoreFactorBaseAccessor scoreFactors() {
    if (scoreFactors != null) {
      return scoreFactors;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for credit report setting operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/id/#mdx-id")
  public CreditReportSettingsBaseAccessor settings() {
    if (settings != null) {
      return settings;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set credit report setting accessor
   * @param settings
   */
  public void setSettings(CreditReportSettingsBaseAccessor settings) {
    this.settings = settings;
  }

  /**
   * Subscribe to credit reporting
   * @return
   */
  @GatewayAPI
  @API(description = "Subscribe to credit reporting")
  public AccessorResponse<CreditReportSettings> subscribe() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Unsubscribe from credit reporting
   * @return
   */
  @GatewayAPI
  @API(description = "Unsubscribe from credit reporting")
  public AccessorResponse<CreditReportSettings> unsubscribe() {
    throw new AccessorMethodNotImplementedException();
  }

}
