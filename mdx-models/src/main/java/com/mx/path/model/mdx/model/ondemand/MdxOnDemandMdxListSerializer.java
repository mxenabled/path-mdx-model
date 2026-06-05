package com.mx.path.model.mdx.model.ondemand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.ondemand.mixins.MixinDefinition;
import com.mx.path.model.mdx.model.ondemand.mixins.XmlSkipInternalAnnotationsIntrospector;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.ValueSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.dataformat.xml.XmlMapper;
import tools.jackson.dataformat.xml.ser.ToXmlGenerator;

/**
 * MdxList serializer for MDX OnDemand
 *
 * <p>Relies on Jackson Mix-ins to control details of serialization. For more information on Jackson Mix-ins see:
 * {@see https://github.com/FasterXML/jackson-docs/wiki/JacksonMixInAnnotations}
 */
@Deprecated
public class MdxOnDemandMdxListSerializer extends ValueSerializer<MdxListWrapper> {

  private final ObjectMapper mapper;

  /**
   * Build a list serializer.
   *
   * @param mixinDefinitions that define Jackson annotations to control serialization.
   */
  @SuppressWarnings("unchecked")
  public MdxOnDemandMdxListSerializer(MixinDefinition... mixinDefinitions) {
    super();

    this.mapper = XmlMapper.builder()
        .addMixIns(
            Arrays.stream(mixinDefinitions)
                .collect(Collectors.toMap(MixinDefinition::getTarget, MixinDefinition::getMixin)))
        .addModule(new SimpleModule("CustomLocalDateModule").addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ISO_DATE)))
        .annotationIntrospector(new XmlSkipInternalAnnotationsIntrospector())
        .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
        .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
        .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .build();
  }

  /**
   * Custom serialize.
   * <p>
   * Wraps response in {@code <mdx></mdx>} if marked as wrapped.
   *
   * @param value
   * @param gen
   * @param context
   * @throws JacksonException
   */
  @Override
  public final void serialize(MdxListWrapper value, JsonGenerator gen, SerializationContext context) throws JacksonException {
    ToXmlGenerator generator = (ToXmlGenerator) gen;

    MdxList<?> list = value.getList();

    if (list.getWrapped()) {
      generator.writeRaw("<mdx version=\"5.0\">\n");
    }

    if (value.getWrapperName() != null && !Objects.equals(value.getWrapperName(), "")) {
      generator.writeRaw("<" + value.getWrapperName() + ">\n");
    }

    for (int i = 0; i < list.size(); i++) {
      // writeValue will set the state of xmlWriter to end, this is needed for Jackson calls to be closed successfully
      if (i == list.size() - 1) {
        mapper.writer().writeValue(generator, list.get(i));
      } else {
        generator.writeRaw(mapper.writer().writeValueAsString(list.get(i)));
      }
    }

    if (value.getWrapperName() != null && !Objects.equals(value.getWrapperName(), "")) {
      generator.writeRaw("</" + value.getWrapperName() + ">\n");
    }

    if (list.getWrapped()) {
      generator.writeRaw("</mdx>\n");
    }
  }
}
