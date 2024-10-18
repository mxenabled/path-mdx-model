package com.mx.path.model.mdx.accessor.feature;

/**
 * Enum representing different features in the application.
 * <p>
 * This enum serves as a centralized definition of features available in
 * the application. Each constant corresponds to a specific feature,
 * allowing for type-safe handling and easy reference throughout the codebase.
 * </p>
 * Usage:
 * <p>
 * Use these feature constants when implementing feature-specific logic,
 * error handling, or when setting headers for error responses.
 * </p>
 */
public enum Feature {
  TRANSFERS("transfers"),
  REMOTE_DEPOSITS("remote_deposits"),
  PAYMENTS("payments");
  // todo:All the feature names will be added here.

  private final String featureName;

  Feature(String featureName) {
    this.featureName = featureName;
  }

  public String getFeatureName() {
    return featureName;
  }
}
