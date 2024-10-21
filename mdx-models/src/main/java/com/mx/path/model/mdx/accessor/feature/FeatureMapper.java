package com.mx.path.model.mdx.accessor.feature;

import java.util.HashMap;
import java.util.Map;

import com.mx.path.core.common.request.Feature;

public class FeatureMapper {

  private static final Map<Feature, String> FEATURE_NAMES = new HashMap<>();

  static {
    FEATURE_NAMES.put(Feature.TRANSFERS, "transfers");
    FEATURE_NAMES.put(Feature.REMOTE_DEPOSITS, "remote_deposits");
  }

  public static String getFeatureName(Feature feature) {
    return FEATURE_NAMES.get(feature);
  }
}
