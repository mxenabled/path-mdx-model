package com.mx.path.api.connect.messaging.remote.models;

import java.lang.reflect.InvocationTargetException;

import lombok.Data;

import com.mx.path.model.mdx.model.account.Account;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @deprecated Use {@link Account}
 */
@Data
@Deprecated
public class RemoteAccount extends Account {
  public RemoteAccount() {
    super();
  }

  public RemoteAccount(Account account) {
    this();

    try {
      BeanUtils.copyProperties(account, this);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException("Error creating RemoteAccount from Account", e);
    }
  }
}
