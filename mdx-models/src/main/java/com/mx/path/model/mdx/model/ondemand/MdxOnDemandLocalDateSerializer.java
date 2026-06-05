package com.mx.path.model.mdx.model.ondemand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

/**
 * Handles serialization of LocalDate for MDX OnDemand responses
 */
public class MdxOnDemandLocalDateSerializer extends StdSerializer<LocalDate> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public MdxOnDemandLocalDateSerializer() {
    this(null);
  }

  public MdxOnDemandLocalDateSerializer(Class<LocalDate> t) {
    super(t);
  }

  @Override
  public final void serialize(LocalDate value, JsonGenerator gen, SerializationContext context) throws JacksonException {
    gen.writeString(FORMATTER.format(value));
  }

}
