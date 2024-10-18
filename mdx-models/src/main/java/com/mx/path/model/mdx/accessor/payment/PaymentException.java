package com.mx.path.model.mdx.accessor.payment;

import com.mx.path.core.common.request.Feature;
import com.mx.path.model.mdx.accessor.errorDescriptor.PaymentErrorDescriptor;
import com.mx.path.model.mdx.accessor.feature.FeatureException;

/**
 * Exception thrown when there is an error related to payment.
 * <p>
 * This exception is used for payment-related errors, allowing for specific
 * error descriptors to be specified.
 * </p>
 */
public class PaymentException extends FeatureException {

  /**
   * Constructs a PaymentException with a specific user message and error descriptor.
   *
   * @param userMessage The message to be displayed to the user.
   * @param errorDescriptor The error descriptor for this exception.
   */
  public PaymentException(String userMessage, PaymentErrorDescriptor errorDescriptor) {
    super(userMessage, Feature.PAYMENTS, errorDescriptor);
  }
}
