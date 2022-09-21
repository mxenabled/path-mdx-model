package com.mx.accessors.ach_transfer;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.models.ach_transfer.Customer;

/**
 * Accessor base for Customer
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/ach_transfer/#customer">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/ach_transfer/#customer")
@Deprecated // This is going to be removed in favor of the new ACH transfer spec: https://developer.mx.com/drafts/mdx/ach_transfer/#mdx-ach-transfer
public abstract class CustomerBaseAccessor extends Accessor {
  public CustomerBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a customer
   * @return
   */
  @GatewayAPI
  @API(description = "Get a customer")
  public AccessorResponse<Customer> get() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Create a customer
   * @param customer
   * @return
   */
  @GatewayAPI
  @API(description = "Create a customer")
  public AccessorResponse<Customer> create(Customer customer) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a customer
   * @param customer
   * @return
   */
  @GatewayAPI
  @API(description = "Update a customer")
  public AccessorResponse<Customer> update(Customer customer) {
    throw new AccessorMethodNotImplementedException();
  }
}
