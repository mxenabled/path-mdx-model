package com.mx.testing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mx.path.core.common.store.Store;

public class HashStore implements Store {

  private Map<String, String> values = new HashMap<>();
  private Map<String, Set<String>> sets = new HashMap<>();

  @Override
  public void delete(String key) {
    values.remove(key);
  }

  @Override
  public void deleteSet(String key, String value) {
    if (sets.containsKey(key)) {
      sets.get(key).remove(value);
    }
  }

  @Override
  public String get(String key) {
    return values.get(key);
  }

  @Override
  public Set<String> getSet(String key) {
    return sets.get(key);
  }

  @Override
  public boolean inSet(String key, String value) {
    if (sets.containsKey(key)) {
      return sets.get(key).contains(value);
    }
    return false;
  }

  @Override
  public void put(String key, String value, long expirySeconds) {
    values.put(key, value);
  }

  @Override
  public void put(String key, String value) {
    put(key, value, 0);
  }

  @Override
  public void putSet(String key, String value, long expirySeconds) {
    sets.computeIfAbsent(key, k -> new HashSet<>()).add(value);
  }

  @Override
  public void putSet(String key, String value) {
    putSet(key, value, 0);
  }

  @Override
  public boolean putIfNotExist(String key, String value, long expirySeconds) {
    if (!values.containsKey(key)) {
      values.put(key, value);
      return true;
    }
    return false;
  }

  @Override
  public boolean putIfNotExist(String key, String value) {
    return putIfNotExist(key, value, 0);
  }
}
