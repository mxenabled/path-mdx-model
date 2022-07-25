package com.mx.testing;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.mx.path.gateway.BaseMdxGateway;
import com.mx.path.gateway.api.account.AccountGateway;
import com.mx.path.gateway.api.id.IdGateway;

@SuperBuilder
public class BaseMdxGatewayImpl extends BaseMdxGateway {
  public BaseMdxGatewayImpl() {
    super();
  }

  @Getter
  @Setter
  private IdGateway id;

  @Getter
  @Setter
  private AccountGateway accounts;
}
