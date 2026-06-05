package com.mx.path.model.mdx.model.ondemand.mixins;

import com.mx.path.core.common.model.Internal;

import tools.jackson.databind.cfg.MapperConfig;
import tools.jackson.databind.introspect.AnnotatedMember;
import tools.jackson.dataformat.xml.JacksonXmlAnnotationIntrospector;

/**
 * Skip rendering of Internal fields.
 */
public class XmlSkipInternalAnnotationsIntrospector extends JacksonXmlAnnotationIntrospector {
  @Override
  public final boolean hasIgnoreMarker(MapperConfig<?> config, AnnotatedMember m) {
    return m.hasAnnotation(Internal.class) || super.hasIgnoreMarker(config, m);
  }
}
