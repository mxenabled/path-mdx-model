package com.mx.path.model.mdx.accessor.feature;

/**
 * ErrorDescriptor representing different error descriptors in the application.
 * <p>
 * This enum provides a centralized way to manage error descriptors used
 * throughout the application. Each constant represents a specific error
 * condition that may arise during application operations.
 * <p>
 * Usage:
 * <p>
 * Use these constants to represent error conditions when throwing
 * exceptions or logging errors, ensuring consistency across the application.
 * </p>
 */
public enum ErrorDescriptor {
  INSUFFICIENT_FUNDS("INSUFFICIENT_FUNDS"),
  ACCOUNT_NOT_FOUND("ACCOUNT_NOT_FOUND"),
  UNKNOWN_ERROR("UNKNOWN_ERROR");
  // todo: All the expected ErrorDescriptors will be added here.

  private final String descriptor;

  ErrorDescriptor(String descriptor) {
    this.descriptor = descriptor;
  }

  public String getDescriptor() {
    return descriptor;
  }
}
