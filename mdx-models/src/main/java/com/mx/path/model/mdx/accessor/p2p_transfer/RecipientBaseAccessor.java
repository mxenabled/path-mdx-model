package com.mx.path.model.mdx.accessor.p2p_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.p2p_transfer.Recipient;

/**
 * Accessor base for p2p transfer recipient operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#recipients")
public class RecipientBaseAccessor extends Accessor {
  public RecipientBaseAccessor() {
  }

  /**
   * Create a recipient
   *
   * @param recipient
   * @return
   */
  @GatewayAPI
  @API(description = "Create a recipient")
  public AccessorResponse<Recipient> create(Recipient recipient) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Delete a recipient
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Delete a recipient")
  public AccessorResponse<Void> delete(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a recipient
   *
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a recipient")
  public AccessorResponse<Recipient> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all recipients
   *
   * @return
   */
  @GatewayAPI
  @API(description = "List all recipients")
  public AccessorResponse<MdxList<Recipient>> list() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Update a recipient
   *
   * @param id
   * @param recipient
   * @return
   */
  @GatewayAPI
  @API(description = "Update a recipient")
  public AccessorResponse<Recipient> update(String id, Recipient recipient) {
    throw new AccessorMethodNotImplementedException();
  }
}
