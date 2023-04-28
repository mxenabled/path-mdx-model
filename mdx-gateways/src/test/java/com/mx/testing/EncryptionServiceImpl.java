package com.mx.testing;

import lombok.Getter;

import com.mx.path.core.common.collection.ObjectMap;
import com.mx.path.core.common.security.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {
  @Getter
  private ObjectMap configurations;

  public EncryptionServiceImpl(ObjectMap configurations) {
    this.configurations = configurations;
  }

  @Override
  public String encrypt(String plaintext) {
    return plaintext;
  }

  @Override
  public String decrypt(String ciphertext) {
    return ciphertext;
  }

  @Override
  public boolean isEncrypted(String text) {
    return true;
  }
}
