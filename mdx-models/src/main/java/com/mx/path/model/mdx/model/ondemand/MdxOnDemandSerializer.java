package com.mx.path.model.mdx.model.ondemand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.path.core.common.model.ModelWrappable;
import com.mx.path.model.mdx.model.ondemand.mixins.MixinDefinition;
import com.mx.path.model.mdx.model.ondemand.mixins.XmlSkipInternalAnnotationsIntrospector;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.dataformat.xml.XmlMapper;
import tools.jackson.dataformat.xml.ser.ToXmlGenerator;

/**
 * Object serializer for MDX OnDemand
 * <p>
 * Relies on Jackson Mix-ins to control details of serialization. For more information on Jackson Mix-ins see:
 * https://github.com/FasterXML/jackson-docs/wiki/JacksonMixInAnnotations
 */
public class MdxOnDemandSerializer<T extends ModelWrappable<?>> extends ValueSerializer<T> {

  private final ObjectMapper mapper;

  /**
   * Build a serializer handler for targetClass.
   *
   * @param mixinDefinitions that define Jackson annotations to control serialization.
   */
  @SuppressWarnings("unchecked")
  public MdxOnDemandSerializer(MixinDefinition... mixinDefinitions) {
    super();

    this.mapper = XmlMapper.builder()
        .addMixIns(Arrays.stream(mixinDefinitions)
            .collect(Collectors.toMap(MixinDefinition::getTarget, MixinDefinition::getMixin)))
        .addModule(new SimpleModule("CustomLocalDateModule").addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_DATE)))
        .annotationIntrospector(new XmlSkipInternalAnnotationsIntrospector())
        .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
        .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .build();
  }

  @Override
  public final void serialize(T value, JsonGenerator gen, SerializationContext serializers) throws JacksonException {
    ToXmlGenerator generator = (ToXmlGenerator) gen;

    if (value.getWrapped()) {
      generator.writeRaw("<mdx version=\"5.0\">\n");
    }

    // writeValue will set the state of xmlWriter to end, this is needed for Jackson calls to be closed successfully
    mapper.writer().writeValue(generator, value);

    if (value.getWrapped()) {
      generator.writeRaw("</mdx>\n");
    }
  }
}
