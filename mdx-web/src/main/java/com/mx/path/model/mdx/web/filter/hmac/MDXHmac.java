package com.mx.path.model.mdx.web.filter.hmac;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Objects;

import com.mx.path.core.common.accessor.UnauthorizedException;
import com.mx.path.model.mdx.web.filter.MultiReadHttpServletRequest;

import org.apache.commons.io.IOUtils;

/**
 * <pre>
 * Implementation of MDXv6 HMAC.
 *
 * https://developer.mx.com/drafts/mdx/id/
 * </pre>
 */
public class MDXHmac {

  // Statics

  private static final int AUTHORIZATION_SEGMENT_COUNT = 3;

  // Public

  /**
   * Verifies HMAC based on request and salt
   * @param request
   * @param salt
   */
  @SuppressWarnings("PMD.CyclomaticComplexity")
  public static void validateRequest(MultiReadHttpServletRequest request, String salt) {
    try {
      if (request.getHeader("Authorization") == null) {
        throw new UnauthorizedException("Authorization header missing", "Authorization header missing");
      }
      if (request.getHeader("Date") == null) {
        throw new UnauthorizedException("Date header missing", "Date header missing");
      }

      String authorization = Objects.requireNonNull(request.getHeader("Authorization"), "Authorization header missing");
      String date = Objects.requireNonNull(request.getHeader("Date"), "Date header missing");

      // Authorization header format: MDX [algorithm] [signature]
      String[] authorizationSplit = authorization.split("\\s+");
      if (authorizationSplit.length != AUTHORIZATION_SEGMENT_COUNT) {
        throw new UnauthorizedException("Invalid Authorization header format", "Invalid Authorization header format");
      }

      String algorithm = authorizationSplit[1];
      String hmacSignature = authorizationSplit[2];

      String contentType = defaultIfNull(request.getHeader("Content-Type"), "");
      String content = defaultIfNull(IOUtils.toString(request.getContentAsByteArray(), "UTF-8"), "");
      String requestURI = request.getRequestURL().toString();

      String contentMD5 = "";
      // Only generate an MD5 if there is content
      if (!Objects.equals("", content)) {
        contentMD5 = HMAC.getMD5Hex(content);
      }
      String hmacData = hmacToSign(request.getMethod(), contentMD5, contentType, date, requestURI);
      String generatedHmacSignature = HMAC.generateHmacSignature(hmacData, salt, algorithm);
      if (!generatedHmacSignature.equalsIgnoreCase(hmacSignature)) {
        throw new UnauthorizedException("HMAC signature mismatch", "HMAC signature mismatch");
      }
    } catch (NumberFormatException | IOException | SignatureException | NoSuchAlgorithmException | InvalidKeyException e) {
      throw new PathHMACException(e.getMessage(), e);
    }
  }

  // Private

  private static String defaultIfNull(String value, String defaultValue) {
    if (value == null) {
      return defaultValue;
    }

    return value;
  }

  private static String hmacToSign(String httpMethod, String contentMD5, String contentType, String date, String restRequestResource) {
    return httpMethod + contentMD5 + contentType + date + restRequestResource;
  }
}
