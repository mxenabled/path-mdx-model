package com.mx.path.model.mdx.accessor.transfer;

import com.mx.path.core.common.request.Feature;
import com.mx.path.model.mdx.accessor.errorDescriptor.TransferErrorDescriptor;
import com.mx.path.model.mdx.accessor.feature.FeatureException;

/**
 * Exception thrown when there is an error related to transfers.
 * <p>
 * This exception is used for transfer-related errors, allowing for specific
 * error descriptors to be specified.
 * </p>
 */
public class TransferException extends FeatureException {

  /**
   * Constructs a TransferException with a specific user message and error descriptor.
   *
   * @param userMessage The message to be displayed to the user.
   * @param errorDescriptor The error descriptor for this exception.
   */
  public TransferException(String userMessage, TransferErrorDescriptor errorDescriptor) {
    super(userMessage, Feature.TRANSFERS, errorDescriptor);
  }
}
