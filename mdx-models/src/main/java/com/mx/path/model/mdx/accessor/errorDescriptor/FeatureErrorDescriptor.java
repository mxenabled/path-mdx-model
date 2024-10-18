package com.mx.path.model.mdx.accessor.errorDescriptor;

/**
 * Marker interface for all feature-specific error descriptor enums.
 * <p>
 * This interface is implemented by all specific error descriptor enums to provide a common way of representing error descriptors.
 * </p>
 */
public interface FeatureErrorDescriptor {
  @Override
  String toString();
}
