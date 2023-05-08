package com.mx.path.service.connection.realtime;

import com.mx.path.gateway.configuration.annotations.ClientID;
import lombok.Data;

import com.mx.path.core.common.configuration.ConfigurationField;

@Data
public class MdxRealtimeConnectionConfiguration {
  @ConfigurationField
  private String clientId;

  @ConfigurationField(secret = true)
  private String apiKey;

  @ConfigurationField(secret = true)
  private String hmacSalt;

  @ConfigurationField
  private String hmacDigest;

  @ClientID
  private String mxClientId;
}
