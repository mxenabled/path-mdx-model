package com.mx.path.service.legacy.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.mx.path.core.context.Session;
import com.mx.path.gateway.context.Scope;

public class AccountBehaviors {

  private Map<String, AccountBehavior> accountBehaviors = new HashMap<>();

  public static AccountBehaviors loadFromSession() {
    AccountBehaviors accountBehaviors = Session.current().getObj(Scope.Session, "accountBehaviors", AccountBehaviors.class);

    return Objects.isNull(accountBehaviors) ? new AccountBehaviors() : accountBehaviors;
  }

  public final Map<String, AccountBehavior> getAccountBehaviors() {
    return accountBehaviors;
  }

  public final void setAccountBehaviors(Map<String, AccountBehavior> accountBehaviors) {
    this.accountBehaviors = accountBehaviors;
  }

  public final String serviceAccountIdToAccountId(AccountBehavior.ServiceIdentifier service, String serviceAccountId) {
    for (Entry<String, AccountBehavior> entry : accountBehaviors.entrySet()) {
      if (serviceAccountId.equals(entry.getValue().getMappedAccountIds().get(service))) {
        return entry.getKey();
      }
    }
    return null;
  }

  public final void saveToSession() {
    Session.current().sputObj(Scope.Session, "accountBehaviors", this);
  }

  /**
   * @param accountId - The associated accountId
   * @param accountBehavior - The associated AccountBehavior
   */
  public final void put(String accountId, AccountBehavior accountBehavior) {
    accountBehaviors.put(accountId, accountBehavior);
  }

  /**
   * @param accountId - The associated accountId
   * @return The associated AccountBehavior
   */
  public final AccountBehavior get(String accountId) {
    return accountBehaviors.get(accountId);
  }
}
