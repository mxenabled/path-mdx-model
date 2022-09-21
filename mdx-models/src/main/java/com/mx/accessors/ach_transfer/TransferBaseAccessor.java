package com.mx.accessors.ach_transfer;

import lombok.AccessLevel;
import lombok.Getter;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.common.models.MdxList;
import com.mx.models.ach_transfer.Transfer;

/**
 * Accessor base for ACH transfer operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/ach_transfer/">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/")
@Deprecated // This is being replaced by: https://developer.mx.com/drafts/mdx/ach_transfer/#mdx-ach-transfer
public abstract class TransferBaseAccessor extends Accessor {

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private CustomerBaseAccessor customer;

  @GatewayAPI
  @Getter(AccessLevel.PROTECTED)
  private FundingSourceBaseAccessor fundingSources;

  public TransferBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * List all transfers
   * @return
   */
  @GatewayAPI
  @API(description = "List all transfers")
  public AccessorResponse<MdxList<Transfer>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a transfer
   * @param transferId
   * @return
   */
  @GatewayAPI
  @API(description = "Get a transfer")
  public AccessorResponse<Transfer> get(String transferId) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create a transfer
   * @param achTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Create a transfer")
  public AccessorResponse<Transfer> create(Transfer achTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a transfer
   * @param achTransfer
   * @return
   */
  @GatewayAPI
  @API(description = "Update a transfer")
  public AccessorResponse<Transfer> update(String transferId, Transfer achTransfer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Accessor for customer operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#customer")
  public CustomerBaseAccessor customer() {
    if (customer != null) {
      return customer;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set customer accessor
   * @param customer
   */
  public void setCustomer(CustomerBaseAccessor customer) {
    this.customer = customer;
  }

  /**
   * Accessor for funding source operations
   * @return accessor
   */
  @API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#funding-sources")
  public FundingSourceBaseAccessor fundingSources() {
    if (fundingSources != null) {
      return fundingSources;
    }

    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Set funding sources accessor
   * @param fundingSources
   */
  public void setFundingSources(FundingSourceBaseAccessor fundingSources) {
    this.fundingSources = fundingSources;
  }
}
