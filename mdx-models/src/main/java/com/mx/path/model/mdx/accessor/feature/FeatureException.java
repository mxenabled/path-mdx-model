package com.mx.path.model.mdx.accessor.feature;

import com.mx.path.core.common.exception.PathRequestException;
import com.mx.path.core.common.request.Feature;
import com.mx.path.model.mdx.accessor.errorDescriptor.FeatureErrorDescriptor;

/**
 * Base class for exceptions related to features in the path-mdx-model project.
 * <p>
 * This exception is designed to be extended for defining feature-specific errors within the Path MDX Model.
 * It allows the inclusion of relevant feature names and error descriptors in exception responses.
 * The class automatically adds HTTP headers such as the feature name and the associated error descriptor
 * to facilitate better error tracking and debugging.
 * <p>
 * Usage:
 * Use the constructor to set a user-friendly error message and provide the specific feature and error descriptor.
 * <p>
 * The exception also includes headers that can be used in HTTP responses to help clients understand the source
 * of the error more clearly.
 * </p>
 */
public abstract class FeatureException extends PathRequestException {

  private final Feature feature;
  private final FeatureErrorDescriptor errorDescriptor;

  protected FeatureException(String userMessage, Feature feature, FeatureErrorDescriptor errorDescriptor) {
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
