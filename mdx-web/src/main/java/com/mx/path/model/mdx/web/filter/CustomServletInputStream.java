package com.mx.path.model.mdx.web.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

/**
 * Wraps request input stream to allow multiple reads
 */
public class CustomServletInputStream extends ServletInputStream {

  // Fields

  private ByteArrayInputStream buffer;

  // Constructors

  public CustomServletInputStream(byte[] contents) {
    this.buffer = new ByteArrayInputStream(contents);
  }

  // ServletInputStream

  @Override
  public final boolean isFinished() {
    return buffer.available() == 0;
  }

  @Override
  public final boolean isReady() {
    return true;
  }

  @Override
  public final void setReadListener(ReadListener listener) {
    throw new RuntimeException("Not implemented!");
  }

  @Override
  public final int read() throws IOException {
    return buffer.read();
  }
}
