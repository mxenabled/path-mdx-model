package com.mx.testing;

import java.util.Set;

import com.mx.common.collections.ObjectMap;
import com.mx.common.store.Store;

public class TestStore implements Store {
  ObjectMap configurations;

  public TestStore(ObjectMap configurations) {
    this.configurations = configurations;
  }

  @Override
  public void delete(String key) {
  }

  @Override
  public void deleteSet(String key, String value) {

  }

  @Override
  public String get(String key) {
    return null;
  }

  @Override
  public Set<String> getSet(String key) {
    return null;
  }

  @Override
  public boolean inSet(String key, String value) {
    return false;
  }

  @Override
  public void put(String key, String value, long expirySeconds) {
  }

  @Override
  public void put(String key, String value) {

  }

  @Override
  public void putSet(String key, String value, long expirySeconds) {

  }

  @Override
  public void putSet(String key, String value) {

  }

  @Override
  public boolean putIfNotExist(String key, String value, long expirySeconds) {
    return false;
  }

  @Override
  public boolean putIfNotExist(String key, String value) {
    return false;
  }
}
