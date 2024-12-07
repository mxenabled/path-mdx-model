package com.mx.path.model.mdx.accessor.errorDescriptor;

/**
 * RemoteDepositErrorDescriptor represents different error conditions related to remote check deposits in the application.
 * <p>
 * This enum provides a centralized way to manage error descriptors for remote deposit operations throughout the application.
 * Each constant represents a specific error condition that may arise during the remote check deposit process, such as
 * issues with the check image, invalid check details, exceeding deposit limits, or insufficient funds in the account.
 * </p>
 * <p>
 * Usage:
 * <p>
 * Use these constants to represent remote deposit-related error conditions when throwing exceptions or logging errors,
 * ensuring consistency and clarity across the application.
 * </p>
 */
public enum RemoteDepositErrorDescriptor implements FeatureErrorDescriptor {
  INVALID_CHECK_IMAGE,
  IMAGE_TOO_LARGE,
  UNSUPPORTED_CHECK_TYPE,
  INVALID_CHECK_DETAILS,
  CHECK_ALREADY_DEPOSITED,
  ACCOUNT_NOT_FOUND,
  INSUFFICIENT_FUNDS,
  MAX_DEPOSIT_LIMIT_EXCEEDED;
  // todo: All the Remote Deposit ErrorDescriptors will be added here.

  @Override
  public String toString() {
    return name();
  }
}
