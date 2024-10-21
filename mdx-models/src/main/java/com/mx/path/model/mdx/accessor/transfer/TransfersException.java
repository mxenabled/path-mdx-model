
package com.mx.path.model.mdx.accessor.transfer;

import com.mx.path.core.common.request.Feature;
import com.mx.path.model.mdx.accessor.feature.ErrorDescriptor;
import com.mx.path.model.mdx.accessor.feature.FeatureException;
import com.mx.path.model.mdx.accessor.feature.FeatureMapper;

/**
 * Exception thrown when there is an error related to transfers.
 * <p>
 * This base exception for transfers sets the appropriate feature name
 * and allows for error descriptors to be specified for more specific
 * transfer-related errors.
 * </p>
 */
public class TransfersException extends FeatureException {

  /**
   * Constructs a TransfersException with a specific user message and error descriptor.
   *
   * @param userMessage The message to be displayed to the user.
   * @param errorDescriptor The error descriptor for this exception.
   */
  public TransfersException(String userMessage, ErrorDescriptor errorDescriptor) {
    super(userMessage, Feature.TRANSFERS, errorDescriptor.getDescriptor());
  }

  /**
   * Returns the name of the feature associated with this exception.
   */
  @Override
  protected String getFeatureName() {
    return FeatureMapper.getFeatureName(Feature.TRANSFERS);
  }
}
