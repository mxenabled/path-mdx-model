package com.mx.path.model.mdx.web.filter.hmac;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Helper class for generating HMAC signatures
 */
public class HMAC {

  private static final Map<String, String> ALGORITHM_MAPPINGS = new HashMap<String, String>();
  private static final HashSet<String> VALID_ALGORITHMS = new HashSet<String>();

  /**
   * Build a list of valid MDX algorithms and their mappings to the HMAC equivalents.
   */
  static {
    ALGORITHM_MAPPINGS.put("md5", "HmacMD5");
    ALGORITHM_MAPPINGS.put("sha1", "HmacSHA1");
    ALGORITHM_MAPPINGS.put("sha256", "HmacSHA256");
    ALGORITHM_MAPPINGS.put("sha384", "HmacSHA384");
    ALGORITHM_MAPPINGS.put("sha512", "HmacSHA512");

    VALID_ALGORITHMS.addAll(ALGORITHM_MAPPINGS.values());
  }

  public static final String getMD5Hex(String bodyToHash) {
    return DigestUtils.md5Hex(bodyToHash);
  }

  /**
   * Generates an HMAC signature
   * @param hmacSalt
   * @param data
   * @param algorithm
   * @return HMAC signature
   * @throws SignatureException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   * @throws UnsupportedEncodingException
   */
  public static final String generateHmacSignature(String data, String hmacSalt, String algorithm)
      throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {

    algorithm = convertAlgorithm(algorithm);
    SecretKeySpec secretKeySpec = new SecretKeySpec(hmacSalt.getBytes("UTF-8"), algorithm);
    Mac mac = Mac.getInstance(algorithm);
    mac.init(secretKeySpec);

    return toHexString(mac.doFinal(data.getBytes("UTF-8")));
  }

  // Private

  /**
   * Converts and validates the HMAC algorithm
   *
   * @param algorithm
   * @return valid HMAC algorithm
   * @throws NoSuchAlgorithmException
   */
  private static String convertAlgorithm(String algorithm) throws NoSuchAlgorithmException {
    if (VALID_ALGORITHMS.contains(algorithm)) {
      return algorithm;
    }

    String newAlgorithm = ALGORITHM_MAPPINGS.get(algorithm);
    if (newAlgorithm == null) {
      throw new NoSuchAlgorithmException("Algorithm not supported by MDX: " + algorithm);
    }

    return newAlgorithm;
  }

  /**
   * Converts byte array to hex string
   * @param bytes
   * @return hex string
   */
  private static String toHexString(byte[] bytes) {
    Formatter formatter = new Formatter();
    for (byte b : bytes) {
      formatter.format("%02x", b);
    }
    String hex = formatter.toString();
    formatter.close();
    return hex;
  }
}
