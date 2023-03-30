package com.mx.path.service.connection.realtime;

import lombok.Data;

import com.mx.common.configuration.ConfigurationField;

@Data
public class MdxRealtimeConnectionConfiguration {
  @ConfigurationField(value = "clientId")
  private String clientId;

  @ConfigurationField(value = "apiKey", secret = true)
  private String apiKey;
}
