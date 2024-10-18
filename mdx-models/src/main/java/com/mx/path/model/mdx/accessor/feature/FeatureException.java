package com.mx.path.model.mdx.accessor.feature;

import com.mx.path.core.common.exception.PathRequestException;

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

  private final String feature;
  private final String errorDescriptor;

  protected FeatureException(String userMessage, Feature feature, String errorDescriptor) {
    super(userMessage);
    this.feature = feature.getFeatureName();
    this.errorDescriptor = errorDescriptor;
    initialize();
  }

  /**
   * Abstract method to be implemented by subclasses to provide the feature name.
   *
   * @return The name of the feature.
   */
  protected abstract String getFeatureName();

  /**
   * Initializes headers for the exception.
   */
  private void initialize() {
    withHeader("MX-Feature", feature);
    withHeader("MX-Feature-Error", errorDescriptor);
  }

}
