package com.mx.path.model.mdx.accessor.p2p_transfer;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.account.Account;

/**
 * Accessor base for P2P transfer account operations
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#p2p-transfer-accounts")
public class AccountBaseAccessor extends Accessor {
  public AccountBaseAccessor() {
  }

  /**
   * Lists all p2p transfer accounts
   *
   * @return MdxList&lt;Account&gt;
   */
  @GatewayAPI
  @API(description = "Lists all accounts for P2P transfers")
  public AccessorResponse<MdxList<Account>> list() {
    throw new AccessorMethodNotImplementedException();
  }
}
