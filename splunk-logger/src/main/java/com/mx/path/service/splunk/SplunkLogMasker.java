package com.mx.path.service.splunk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplunkLogMasker {

  private static final String MASK = "**MASKED**";

  /**
   * Masked Header Keys
   *
   * Description:
   *
   *   The values for headers that match these names (case-insensitive) will be
   *   replaced with MASK
   */
  private static final String[] HEADERKEYS = {
      "x-csrf-token",
      "x-request-token",
      "asp.net_sessionid",
      "mx-session-key"
  };

  /**
   * Masked Cookie Keys
   *
   * Description:
   *
   *   The values for cookies that match these names (case-insensitive) will be
   *   replaced with MASK
   */
  private static final String[] COOKIEKEYS = {
      "asp.net_sessionid"
  };

  /**
   * Payload Masking Regex collection
   *
   * Description:
   *
   *   These regexes will all be applied in order. For each match the 1st group will
   *   be replaced with MASK.
   *
   * Example:
   *
   *   Mask Regex: /\"password\":\"([^\"]+)\"/
   *        >> portion to be masked ^^^^^^
   *
   * Using ` for "" here for readability. These are replaced with \" at load time.
   */
  private static final String[] PAYLOADPATTERNS = {
      "`antiforgerytoken`\\s*:\\s*`([^`]+)`".replaceAll("`", "\\\\\""),
      "`token`\\s*:\\s*`([^`]+)`".replaceAll("`", "\\\\\""),
      "`password`\\s*:\\s*`([^`]+)`".replaceAll("`", "\\\\\"")
  };

  private static final HashSet<String> HEADERKEYSET = new HashSet<>();
  private static final List<Pattern> PAYLOADPATTERNSET = new ArrayList<>();
  private static final List<Pattern> COOKIEPATTERNSET = new ArrayList<>();

  /**
   * Register key for cookie value to mask
   *
   * @param key
   */
  public static void registerCookieKey(String key) {
    COOKIEPATTERNSET.add(Pattern.compile(key.toLowerCase(Locale.ENGLISH) + "=([^;]+)", Pattern.CASE_INSENSITIVE));
  }

  /**
   * Register key name for header to mask
   *
   * @param key
   */
  public static void registerHeaderKey(String key) {
    HEADERKEYSET.add(key.toLowerCase(Locale.ENGLISH));
  }

  /**
   * Register new regex pattern for masking request/response payloads
   *
   * Description:
   *
   *   These regexes will all be applied in order. For each match the 1st group will
   *   be replaced with MASK.
   *
   * Example:
   *
   *   Mask Regex: /\"password\":\"([^\"]+)\"/
   *        >> portion to be masked ^^^^^^
   *
   * @param pattern
   */
  public static void registerPayloadPattern(String pattern) {
    Pattern mask = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    PAYLOADPATTERNSET.add(mask);
  }

  public static void resetPatterns() {
    HEADERKEYSET.clear();
    PAYLOADPATTERNSET.clear();
    COOKIEPATTERNSET.clear();

    Collections.addAll(HEADERKEYSET, HEADERKEYS);
    for (String pattern : PAYLOADPATTERNS) {
      registerPayloadPattern(pattern);
    }

    for (String key : COOKIEKEYS) {
      registerCookieKey(key);
    }
  }

  static {
    resetPatterns();
  }

  // Public

  public final String maskHeaderValue(String header, String value) {
    if (header.contains("Cookie") || header.contains("Set-Cookie")) {
      return applyPatternsToPayload(value, COOKIEPATTERNSET);
    }
    if (HEADERKEYSET.contains(header.toLowerCase(Locale.ENGLISH))) {
      return MASK;
    }

    return value;
  }

  /**
   * Apply regex masks to payload.
   *
   * @param payload the payload to mask
   * @return the masked payload
   */
  public final String maskPayload(String payload) {
    return applyPatternsToPayload(payload, PAYLOADPATTERNSET);
  }

  // Private

  private String applyPatternsToPayload(String payload, List<Pattern> patterns) {
    for (Pattern p : patterns) {
      Matcher m = p.matcher(payload);
      int start = 0;

      while (m.find(start)) {
        String patternMatch = m.group();

        patternMatch = patternMatch.replace(m.group(1), MASK);
        payload = payload.replace(m.group(), patternMatch);
        start = m.start() + 1;
      }
    }

    return payload;
  }
}
