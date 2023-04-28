package com.mx.path.model.mdx.model.ondemand.mixins;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlAnnotationIntrospector;
import com.mx.path.core.common.model.Internal;

/**
 * Skip rendering of Internal fields.
 */
public class XmlSkipInternalAnnotationsIntrospector extends JacksonXmlAnnotationIntrospector {
  @Override
  public final boolean hasIgnoreMarker(AnnotatedMember m) {
    return m.hasAnnotation(Internal.class) || super.hasIgnoreMarker(m);
  }
}
