package com.mx.path.model.mdx.web.filter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * Wraps HttpServletRequest and allows the contents to be read multiple times.
 */
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

  // Fields

  private ByteArrayOutputStream cachedBytes;

  // Constructor

  public MultiReadHttpServletRequest(HttpServletRequest request) {
    super(request);
  }

  // Public

  public final byte[] getContentAsByteArray() throws IOException {
    if (cachedBytes == null) {
      cacheInputStream();
    }
    return cachedBytes.toByteArray();
  }

  // HttpServletRequestWrapper

  @Override
  public final ServletInputStream getInputStream() throws IOException {
    if (cachedBytes == null) {
      cacheInputStream();
    }
    return new CustomServletInputStream(cachedBytes.toByteArray());
  }

  @Override
  public final BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream(), "UTF-8"));
  }

  // Private

  private void cacheInputStream() throws IOException {
    cachedBytes = new ByteArrayOutputStream();
    IOUtils.copy(super.getInputStream(), cachedBytes);
  }
}
