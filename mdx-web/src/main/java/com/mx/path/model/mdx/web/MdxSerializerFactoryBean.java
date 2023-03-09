package com.mx.path.model.mdx.web;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.models.Resources;

import org.springframework.beans.factory.FactoryBean;

public class MdxSerializerFactoryBean implements FactoryBean<Gson> {
  public MdxSerializerFactoryBean() {
  }

  @Override
  public final Gson getObject() throws Exception {
    GsonBuilder baseGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setDateFormat("YYYY-MM-dd").setPrettyPrinting();

    Resources.registerResources(baseGson);

    return baseGson.create();
  }

  @Override
  public final Class<?> getObjectType() {
    return Gson.class;
  }

  @Override
  public final boolean isSingleton() {
    return false;
  }
}
