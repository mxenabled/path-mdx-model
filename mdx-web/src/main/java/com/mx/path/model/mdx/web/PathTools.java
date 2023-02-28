package com.mx.path.model.mdx.web;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mx.common.accessors.RequestValidationException;

/**
 * Utility methods for dealing with raw request paths.
 * <p>
 * These mostly exist to help parse raw paths in Filters which run outside of a controller.
 */
public class PathTools {

  // Statics

  /**
   * Detect all paths that require a session.
   */
  private static final List<Predicate<String>> SESSION_REQUIRED_PATHS = new ArrayList<>();

  static {
    SESSION_REQUIRED_PATHS.add(Pattern.compile("^\\/[^\\/]+\\/users\\/[^\\/]+\\/.*$").asPredicate()); // All user-scope routes
    SESSION_REQUIRED_PATHS.add(Pattern.compile("^\\/[^\\/]+\\/authentications\\/(?!start$).+$").asPredicate()); // Logout
  }

  /**
   * Detect all paths that require a successful authentication.
   */
  private static final List<Predicate<String>> AUTHENTICATION_REQUIRED_PATHS = new ArrayList<>();

  static {
    AUTHENTICATION_REQUIRED_PATHS.add(Pattern.compile("^\\/[^\\/]+\\/users\\/[^\\/]+\\/.*$").asPredicate()); // All user-scope routes
    AUTHENTICATION_REQUIRED_PATHS.add(Pattern.compile("^\\/[^\\/]+\\/accounts\\/?$").asPredicate());        // MDX OnDemand Accounts
    AUTHENTICATION_REQUIRED_PATHS.add(Pattern.compile("^\\/[^\\/]+\\/accounts\\/.*\\/transactions$").asPredicate());        // MDX OnDemand Accounts
  }

  /**
   * Predicate to detect authentication path. Matches:
   * /{clientid}/authentications
   * /{clientid}/authentications/
   * /{clientid}/authentications/start
   * <p>
   * NOTE:
   * Does NOT match MFA or Logout paths:
   * /{clientid}/authentications/session-1234
   */
  private static final List<Predicate<String>> AUTHENTICATION_PATH = new ArrayList<Predicate<String>>();

  static {
    AUTHENTICATION_PATH.add(Pattern.compile("^\\/[^\\/]+\\/authentications\\/?$").asPredicate());
    AUTHENTICATION_PATH.add(Pattern.compile("^\\/[^\\/]+\\/authentications\\/start?$").asPredicate());
  }

  /**
   * Regex Pattern to extract the clientId from the path.
   * <p>
   * MDX paths start with /:clientId/
   */
  private static final Pattern PATH_CLIENT_GUID_PATTERN = Pattern.compile("^\\/((?<clientId>[^\\/]+)\\/.*)");
  private static final Pattern PATH_SESSION_ID_PATTERN = Pattern.compile("^\\/[^\\/]+\\/authentications\\/(?<sessionId>[^\\/]+)\\/sso\\/*");

  /**
   * Extract the ClientId from a path
   * <p>
   * Returns clientID from /{clientID}/users/bob/accounts
   */
  public static String getClientIdFromPath(String path) {
    String clientId = null;

    Matcher clientGuidMatcher = PATH_CLIENT_GUID_PATTERN.matcher(path);
    if (clientGuidMatcher.matches()) {
      clientId = clientGuidMatcher.group("clientId");
    } else {
      throw new RequestValidationException("Invalid path. clientId not provided", "Invalid path. clientId not provided");
    }

    return clientId;
  }

  /**
   * Returns true if the path is for creating a new session
   */
  public static boolean isAuthenticationPath(String path) {
    return AUTHENTICATION_PATH.stream().anyMatch(p -> p.test(path));
  }

  /**
   * Returns true is the path requires prior authentication
   */
  public static boolean isSessionRequiredPath(String path) {
    return SESSION_REQUIRED_PATHS.stream().anyMatch(p -> p.test(path));
  }

  /**
   * Returns true is the path requires prior authentication
   */
  public static boolean isAuthenticationRequiredPath(String path) {
    return AUTHENTICATION_REQUIRED_PATHS.stream().anyMatch(p -> p.test(path));
  }

  public static String extractSessionId(String path) {
    Matcher sessionIdMatcher = PATH_SESSION_ID_PATTERN.matcher(path);
    if (sessionIdMatcher.matches()) {
      return sessionIdMatcher.group("sessionId");
    }

    return null;
  }
}
