package com.mx.path.model.mdx.accessor.feature;

import com.mx.path.core.common.exception.PathRequestException;
import com.mx.path.core.common.request.Feature;

/**
 * Base class for exceptions related to features in the path-mdx-model project.
 * <p>
 * This exception serves as a foundation for defining feature-specific errors.
 * It sets appropriate headers for responses, including the feature name and
 * error descriptor.
 * <p>
 * Usage:
 * <p>
 * To create a new feature-specific exception, extend this class and implement
 * the {@code getFeatureName} method to return the corresponding feature name.
 * Use the constructor to set the user message and error descriptor.
 */
public abstract class FeatureException extends PathRequestException {

  private final Feature feature;
  private final ErrorDescriptor errorDescriptor;

  protected FeatureException(String userMessage, Feature feature, ErrorDescriptor errorDescriptor) {
    super(userMessage);
    this.feature = feature;
    this.errorDescriptor = errorDescriptor;
    initialize();
  }

  /**
   * Initializes headers for the exception.
   */
  private void initialize() {
    withHeader("MX-Feature", feature.toString().toLowerCase());
    withHeader("MX-Feature-Error", errorDescriptor.toString());
  }
}
