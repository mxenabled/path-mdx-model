package com.mx.path.model.mdx.web.filter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

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
