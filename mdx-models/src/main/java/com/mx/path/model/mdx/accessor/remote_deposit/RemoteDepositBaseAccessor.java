package com.mx.path.model.mdx.accessor.remote_deposit;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.remote_deposit.RemoteDeposit;

/**
 * Accessor base for remote deposits
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/remote_deposit/#mdx-remote-deposit">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/remote_deposit/#mdx-remote-deposit")
public abstract class RemoteDepositBaseAccessor extends Accessor {

  public RemoteDepositBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Create a remote deposit
   * @param remoteDeposit
   * @return
   */
  @GatewayAPI
  @API(description = "Create a remote deposit")
  public AccessorResponse<RemoteDeposit> create(RemoteDeposit remoteDeposit) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Get a remote deposit
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a remote deposit")
  public AccessorResponse<RemoteDeposit> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List deposit accounts for remote deposits
   * @return
   */
  @GatewayAPI
  @API(description = "List deposit accounts for remote deposits")
  public AccessorResponse<MdxList<Account>> accounts() {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List all remote deposits
   * @return
   */
  @GatewayAPI
  @API(description = "List all remote deposits")
  public AccessorResponse<MdxList<RemoteDeposit>> list() {
    throw new AccessorMethodNotImplementedException();
  }

}
