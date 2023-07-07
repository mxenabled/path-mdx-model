package com.mx.path.model.mdx.model;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mx.path.core.common.model.ModelWrappable;

public class ModelWrappableSerializer implements JsonDeserializer<ModelWrappable<?>>, JsonSerializer<ModelWrappable<?>> {
  private final Gson gson;
  private final String key;

  public ModelWrappableSerializer(String newKey) {
    gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(LocalDate.class, new MdxLocalDateSerializer())
        .registerTypeAdapter(char[].class, new CharArrayAdapter())
        .setDateFormat("YYYY-MM-dd")
        .addSerializationExclusionStrategy(new ExcludeInternalFields())
        .create();

    key = newKey;
  }

  @Override
  public final ModelWrappable<?> deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {

    if (!json.isJsonObject()) {
      throw new JsonParseException("Body not an object");
    }

    JsonObject wrapper = json.getAsJsonObject();
    JsonElement objElement = wrapper.get(key);

    // Not wrapped
    if (objElement == null) {
      return gson.fromJson(wrapper, typeOfT);
    }

    // Wrapped
    return gson.fromJson(objElement, typeOfT);
  }

  @Override
  public final JsonElement serialize(ModelWrappable<?> src, Type typeOfSrc, JsonSerializationContext context) {
    if (src.getWrapped()) {
      JsonObject wrapper = new JsonObject();
      wrapper.add(key, gson.toJsonTree(src));

      return wrapper;
    } else {
      return gson.toJsonTree(src);
    }
  }
}
