package com.mx.path.model.mdx.accessor.feature;

/**
 * ErrorDescriptor representing different error descriptors in the application.
 * <p>
 * This enum provides a centralized way to manage error descriptors used
 * throughout the application. Each constant represents a specific error
 * condition that may arise during application operations.
 * </p>
 * <p>
 * Usage:
 * <p>
 * Use these constants to represent error conditions when throwing
 * exceptions or logging errors, ensuring consistency across the application.
 * </p>
 */
public enum ErrorDescriptor {
  INSUFFICIENT_FUNDS,
  ACCOUNT_NOT_FOUND,
  UNKNOWN_ERROR;
  // todo: All the expected ErrorDescriptors will be added here.

  @Override
  public String toString() {
    return name();
  }
}
