package com.mx.path.model.mdx.accessor.errorDescriptor;

/**
 * TransferErrorDescriptor represents different error conditions related to money transfers in the application.
 * <p>
 * This enum provides a centralized way to manage error descriptors for transfer operations throughout the application.
 * Each constant represents a specific error condition that may arise during the transfer process, such as issues with
 * insufficient funds, invalid account information, transfer limits, and more.
 * </p>
 * <p>
 * Usage:
 * <p>
 * Use these constants to represent transfer-related error conditions when throwing exceptions or logging errors,
 * ensuring consistency and clarity across the application.
 * </p>
 */
public enum TransferErrorDescriptor implements FeatureErrorDescriptor {
  INSUFFICIENT_FUNDS,
  ACCOUNT_NOT_FOUND,
  INVALID_ACCOUNT_DETAILS,
  TRANSFER_FAILED,
  DUPLICATE_TRANSFER;
  // todo: All the Transfer realted ErrorDescriptors will be added here.

  @Override
  public String toString() {
    return name();
  }
}
