package com.mx.path.service.connection.realtime.model;

import lombok.Data;

import com.mx.common.http.HttpStatus;

@Data
public class MdxRealtimeResponse<T> {
  private T object;
  private HttpStatus status;

  public MdxRealtimeResponse(HttpStatus status) {
    this.status = status;
  }
}
