package com.mx.path.model.mdx.accessor.errorDescriptor;

/**
 * PaymentErrorDescriptor represents different error conditions related to payment processing in the application.
 * <p>
 * This enum provides a centralized way to manage payment-related error descriptors used throughout the application.
 * Each constant represents a specific error condition that may arise during payment operations, such as insufficient funds,
 * invalid payment methods, or payment limit issues.
 * </p>
 * <p>
 * Usage:
 * <p>
 * Use these constants to represent payment-related error conditions when throwing exceptions or logging errors,
 * ensuring consistency and clarity across the application.
 * </p>
 */
public enum PaymentErrorDescriptor implements FeatureErrorDescriptor {
  INSUFFICIENT_FUNDS,
  INVALID_PAYMENT_METHOD,
  PAYMENT_LIMIT_EXCEEDED;
  // todo: All the Payment related ErrorDescriptors will be added here.

  @Override
  public String toString() {
    return name();
  }
}
