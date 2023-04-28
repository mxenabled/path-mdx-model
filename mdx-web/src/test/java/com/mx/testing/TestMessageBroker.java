package com.mx.testing;

import lombok.Getter;

import com.mx.path.core.common.collection.ObjectMap;
import com.mx.path.core.common.messaging.EventListener;
import com.mx.path.core.common.messaging.MessageBroker;
import com.mx.path.core.common.messaging.MessageResponder;

public class TestMessageBroker implements MessageBroker {
  @Getter
  private final ObjectMap configurations;

  public TestMessageBroker(ObjectMap configurations) {
    this.configurations = configurations;
  }

  @Override
  public String request(String channel, String payload) {
    return "response";
  }

  @Override
  public void publish(String channel, String payload) {

  }

  @Override
  public void registerResponder(String channel, MessageResponder responder) {

  }

  @Override
  public void registerListener(String channel, EventListener listener) {

  }
}
