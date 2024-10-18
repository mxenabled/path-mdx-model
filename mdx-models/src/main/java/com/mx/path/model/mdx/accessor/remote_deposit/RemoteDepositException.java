package com.mx.path.model.mdx.accessor.remote_deposit;

import com.mx.path.core.common.request.Feature;
import com.mx.path.model.mdx.accessor.errorDescriptor.RemoteDepositErrorDescriptor;
import com.mx.path.model.mdx.accessor.feature.FeatureException;

/**
 * Exception thrown when there is an error related to remote deposit.
 * <p>
 * This exception is used for remote deposit-related errors, allowing for specific
 * error descriptors to be specified.
 * </p>
 */
public class RemoteDepositException extends FeatureException {

  /**
   * Constructs a RemoteDepositException with a specific user message and error descriptor.
   *
   * @param userMessage The message to be displayed to the user.
   * @param errorDescriptor The error descriptor for this exception.
   */
  public RemoteDepositException(String userMessage, RemoteDepositErrorDescriptor errorDescriptor) {
    super(userMessage, Feature.REMOTE_DEPOSITS, errorDescriptor);
  }
}
